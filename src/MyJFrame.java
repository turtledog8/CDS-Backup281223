import graph.MyGraph;
import model.Station;
import traversal.astar.AStarAlgorithm;
import traversal.dijkstra.DijkstraAlgorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Window with a map of the Netherlands. It shows every station as a red dot and can
 * draw the shortest path between two stations, using the same Manager2 as the console.
 * The map can be zoomed with the mouse wheel and moved by dragging, clicking a station shows its name and code.
 */
public class MyJFrame extends JFrame {

    // the area of the world the map image covers, change these if the dots are off
    private static final double MAP_WEST = 3.015;
    private static final double MAP_EAST = 7.497;
    private static final double MAP_NORTH = 53.714;
    private static final double MAP_SOUTH = 50.592;

    private static final double MAX_ZOOM = 20;
    private static final int HIT_RADIUS = 8; // how close to a dot a click has to be, in pixels

    private final Manager2 manager;
    private final MapPanel mapPanel;
    private final JTextArea resultTextArea;
    private final JTextField startField = new JTextField(8);
    private final JTextField endField = new JTextField(8);
    private final JComboBox<String> algorithmBox = new JComboBox<>(new String[]{"Dijkstra", "A*"});

    private List<Station> path = new ArrayList<>();

    public MyJFrame(Manager2 manager) {
        this.manager = manager;

        setTitle("Shortest Path Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 700);
        setMinimumSize(new Dimension(700, 500));

        mapPanel = new MapPanel();
        mapPanel.setPreferredSize(new Dimension(800, 500));
        resultTextArea = new JTextArea(6, 20);
        resultTextArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addToInputPanel(inputPanel, new JLabel("Start station code:"));
        addToInputPanel(inputPanel, startField);
        addToInputPanel(inputPanel, new JLabel("End station code:"));
        addToInputPanel(inputPanel, endField);
        addToInputPanel(inputPanel, new JLabel("Algorithm:"));
        addToInputPanel(inputPanel, algorithmBox);

        JButton findButton = new JButton("Find Shortest Path");
        findButton.addActionListener(e -> findPath());
        addToInputPanel(inputPanel, findButton);

        JButton clearButton = new JButton("Clear Path");
        clearButton.addActionListener(e -> {
            path = new ArrayList<>();
            resultTextArea.setText("");
            mapPanel.repaint();
        });
        addToInputPanel(inputPanel, clearButton);

        inputPanel.add(Box.createVerticalStrut(15));
        addToInputPanel(inputPanel, new JLabel("Map:"));

        JButton zoomInButton = new JButton("Zoom In (+)");
        zoomInButton.addActionListener(e -> mapPanel.zoomAtCenter(1.5));
        addToInputPanel(inputPanel, zoomInButton);

        JButton zoomOutButton = new JButton("Zoom Out (-)");
        zoomOutButton.addActionListener(e -> mapPanel.zoomAtCenter(1 / 1.5));
        addToInputPanel(inputPanel, zoomOutButton);

        JButton resetButton = new JButton("Reset View");
        resetButton.addActionListener(e -> mapPanel.resetView());
        addToInputPanel(inputPanel, resetButton);

        // the map takes all the extra space when the window is resized
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mapPanel, new JScrollPane(resultTextArea));
        splitPane.setResizeWeight(1.0);

        add(inputPanel, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);
    }

    // keeps the components on the left the same width and stops them from stretching in height
    private void addToInputPanel(JPanel panel, JComponent component) {
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, component.getPreferredSize().height));
        panel.add(component);
        panel.add(Box.createVerticalStrut(4));
    }

    private void findPath() {
        Station start = manager.findStationByCode(startField.getText().trim());
        Station end = manager.findStationByCode(endField.getText().trim());
        MyGraph<Station> graph = manager.getGraph();

        if (start == null || end == null) {
            resultTextArea.setText("Invalid station codes.");
            return;
        }
        if (!graph.containsVertex(start) || !graph.containsVertex(end)) {
            resultTextArea.setText("Start or end station not in the graph.");
            return;
        }

        List<Station> found = new ArrayList<>();
        if ("Dijkstra".equals(algorithmBox.getSelectedItem())) {
            for (Station station : new DijkstraAlgorithm<Station>().findShortestPath(graph, start, end)) {
                found.add(station);
            }
        } else {
            found.addAll(new AStarAlgorithm<>(graph).findShortestPath(start, end));
        }

        path = found;
        mapPanel.fitPath();

        if (path.isEmpty()) {
            resultTextArea.setText("No path found.");
            return;
        }

        double distance = 0;
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            text.append(path.get(i).getNameMedium());
            if (i < path.size() - 1) {
                text.append(" -> ");
                distance += graph.getWeight(path.get(i), path.get(i + 1));
            }
        }
        resultTextArea.setText("Shortest path from " + start.getNameMedium() + " to " + end.getNameMedium()
                + ":\n" + text + "\nTotal distance: " + (int) distance + " units");
    }

    private class MapPanel extends JPanel {
        private BufferedImage mapImage;

        // zoom 1 shows the whole map in the panel, the center is a position on the image from 0 to 1
        private double zoom = 1.0;
        private double centerX = 0.5;
        private double centerY = 0.5;

        private int lastMouseX, lastMouseY, pressMouseX, pressMouseY;
        private boolean dragging;

        MapPanel() {
            try {
                mapImage = ImageIO.read(new File(Manager2.resolvePath("src/resources/908px-Netherlands_location_map.svg.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            MouseAdapter mouse = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pressMouseX = lastMouseX = e.getX();
                    pressMouseY = lastMouseY = e.getY();
                    dragging = false;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    // a few pixels of movement still count as a click
                    if (Math.abs(e.getX() - pressMouseX) > 4 || Math.abs(e.getY() - pressMouseY) > 4) {
                        dragging = true;
                    }
                    if (dragging && mapImage != null) {
                        double scale = scale();
                        centerX -= (e.getX() - lastMouseX) / (mapImage.getWidth() * scale);
                        centerY -= (e.getY() - lastMouseY) / (mapImage.getHeight() * scale);
                        clampCenter();
                        repaint();
                    }
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (!dragging) {
                        Station station = stationAt(e.getX(), e.getY());
                        if (station != null) {
                            showStationPopup(station, e.getX(), e.getY());
                        }
                    }
                    dragging = false;
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    setCursor(stationAt(e.getX(), e.getY()) != null
                            ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                            : Cursor.getDefaultCursor());
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    zoomAt(e.getX(), e.getY(), Math.pow(1.2, -e.getPreciseWheelRotation()));
                }
            };
            addMouseListener(mouse);
            addMouseMotionListener(mouse);
            addMouseWheelListener(mouse);
        }

        // ---- view ----

        // how many pixels one image pixel takes at the current zoom, zoom 1 fits the whole image
        private double scale() {
            return Math.min((double) getWidth() / mapImage.getWidth(), (double) getHeight() / mapImage.getHeight()) * zoom;
        }

        private void clampCenter() {
            centerX = Math.max(0, Math.min(1, centerX));
            centerY = Math.max(0, Math.min(1, centerY));
        }

        // zooms and keeps the point under the mouse where it is
        void zoomAt(int panelX, int panelY, double factor) {
            if (mapImage == null || getWidth() == 0) {
                return;
            }
            double scale = scale();
            double pointX = centerX + (panelX - getWidth() / 2.0) / (mapImage.getWidth() * scale);
            double pointY = centerY + (panelY - getHeight() / 2.0) / (mapImage.getHeight() * scale);

            zoom = Math.max(1, Math.min(MAX_ZOOM, zoom * factor));

            scale = scale();
            centerX = pointX - (panelX - getWidth() / 2.0) / (mapImage.getWidth() * scale);
            centerY = pointY - (panelY - getHeight() / 2.0) / (mapImage.getHeight() * scale);
            clampCenter();
            repaint();
        }

        void zoomAtCenter(double factor) {
            zoomAt(getWidth() / 2, getHeight() / 2, factor);
        }

        void resetView() {
            zoom = 1.0;
            centerX = 0.5;
            centerY = 0.5;
            repaint();
        }

        // zooms so that the whole path is visible
        void fitPath() {
            if (mapImage != null && getWidth() > 0) {
                double minX = 1, maxX = 0, minY = 1, maxY = 0;
                boolean any = false;
                for (Station station : path) {
                    if (isInBounds(station)) {
                        any = true;
                        minX = Math.min(minX, fractionX(station));
                        maxX = Math.max(maxX, fractionX(station));
                        minY = Math.min(minY, fractionY(station));
                        maxY = Math.max(maxY, fractionY(station));
                    }
                }
                if (any) {
                    centerX = (minX + maxX) / 2;
                    centerY = (minY + maxY) / 2;

                    double baseScale = scale() / zoom;
                    double zoomX = getWidth() * 0.8 / (Math.max(maxX - minX, 0.02) * mapImage.getWidth() * baseScale);
                    double zoomY = getHeight() * 0.8 / (Math.max(maxY - minY, 0.02) * mapImage.getHeight() * baseScale);
                    zoom = Math.max(1, Math.min(MAX_ZOOM, Math.min(zoomX, zoomY)));
                }
            }
            repaint();
        }

        // ---- positions ----

        // the station position on the image, from 0 to 1
        private double fractionX(Station station) {
            return (station.getLongitude() - MAP_WEST) / (MAP_EAST - MAP_WEST);
        }

        private double fractionY(Station station) {
            return (MAP_NORTH - station.getLatitude()) / (MAP_NORTH - MAP_SOUTH);
        }

        private int panelX(Station station) {
            return (int) Math.round(getWidth() / 2.0 + (fractionX(station) - centerX) * mapImage.getWidth() * scale());
        }

        private int panelY(Station station) {
            return (int) Math.round(getHeight() / 2.0 + (fractionY(station) - centerY) * mapImage.getHeight() * scale());
        }

        private boolean isInBounds(Station station) {
            return station.getLongitude() >= MAP_WEST && station.getLongitude() <= MAP_EAST
                    && station.getLatitude() >= MAP_SOUTH && station.getLatitude() <= MAP_NORTH;
        }

        // only the Netherlands gets dots, foreign stations can still fall inside the rectangle
        private boolean isOnMap(Station station) {
            return "NL".equals(station.getCountry()) && isInBounds(station);
        }

        private Station stationAt(int mouseX, int mouseY) {
            if (mapImage == null || getWidth() == 0) {
                return null;
            }
            Station nearest = null;
            double nearestDistance = HIT_RADIUS * HIT_RADIUS;
            for (Station station : manager.getAllStationsList()) {
                if (!isOnMap(station)) {
                    continue;
                }
                double dx = panelX(station) - mouseX;
                double dy = panelY(station) - mouseY;
                double distance = dx * dx + dy * dy;
                if (distance <= nearestDistance) {
                    nearestDistance = distance;
                    nearest = station;
                }
            }
            return nearest;
        }

        private void showStationPopup(Station station, int x, int y) {
            JPopupMenu menu = new JPopupMenu();

            JLabel label = new JLabel(station.getNameMedium() + " (" + station.getCode().toUpperCase() + ")");
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            menu.add(label);
            menu.addSeparator();

            JMenuItem setStart = new JMenuItem("Set as start");
            setStart.addActionListener(e -> startField.setText(station.getCode().toUpperCase()));
            menu.add(setStart);

            JMenuItem setEnd = new JMenuItem("Set as end");
            setEnd.addActionListener(e -> endField.setText(station.getCode().toUpperCase()));
            menu.add(setEnd);

            menu.show(this, x, y);
        }

        // ---- drawing ----

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mapImage == null) {
                g.drawString("Map image could not be loaded", 20, 20);
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            double scale = scale();
            int width = (int) Math.round(mapImage.getWidth() * scale);
            int height = (int) Math.round(mapImage.getHeight() * scale);
            int left = (int) Math.round(getWidth() / 2.0 - centerX * mapImage.getWidth() * scale);
            int top = (int) Math.round(getHeight() / 2.0 - centerY * mapImage.getHeight() * scale);

            g2.drawImage(mapImage, left, top, width, height, null);

            // the dots get a bit bigger when zoomed in
            int radius = (int) Math.min(6, 2 + zoom / 4);
            g2.setColor(Color.RED);
            for (Station station : manager.getAllStationsList()) {
                if (isOnMap(station)) {
                    int x = panelX(station);
                    int y = panelY(station);
                    if (x >= -radius && x <= getWidth() + radius && y >= -radius && y <= getHeight() + radius) {
                        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
                    }
                }
            }

            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.BLUE);
            for (int i = 0; i < path.size() - 1; i++) {
                Station a = path.get(i);
                Station b = path.get(i + 1);
                if (isInBounds(a) && isInBounds(b)) {
                    g2.drawLine(panelX(a), panelY(a), panelX(b), panelY(b));
                }
            }

            // start in green and end in orange
            if (path.size() > 1) {
                drawMarker(g2, path.get(0), Color.GREEN.darker());
                drawMarker(g2, path.get(path.size() - 1), Color.ORANGE);
            }

            g2.dispose();
        }

        private void drawMarker(Graphics2D g2, Station station, Color color) {
            if (isInBounds(station)) {
                g2.setColor(color);
                g2.fillOval(panelX(station) - 7, panelY(station) - 7, 14, 14);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawOval(panelX(station) - 7, panelY(station) - 7, 14, 14);
            }
        }
    }
}
