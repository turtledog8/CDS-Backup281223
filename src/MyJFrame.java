//import Model.Station;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class MyJFrame extends JFrame {
//
//    private JTextArea resultTextArea;
//    private MapPanel mapPanel;
//    private Manager manager;
//
//    public MyJFrame() {
//        manager = new Manager();
//
//        // Set up JFrame
//        setTitle("Shortest Path Finder");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Set a fixed size for the initial launch
//        setSize(800, 600);
//
//        // Create components
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//
//        // Create buttons for the menu
//        JButton searchStationButton = new JButton("Search Station");
//        JButton findShortestPathButton = new JButton("Find Shortest Path");
//
//        // Add action listeners to the buttons
//        searchStationButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Call your Manager method to perform the search
//                String searchQuery = JOptionPane.showInputDialog("Enter station name:");
//                List<String> searchResult = manager.binarySearchStationByNameFormatted(searchQuery);
//
//                // Display the result in the right text area
//                displaySearchResult(searchResult);
//            }
//        });
//
//        findShortestPathButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showInputDialog();
//            }
//        });
//
//        inputPanel.add(searchStationButton);
//        inputPanel.add(findShortestPathButton);
//
//        // Create the map panel
//        mapPanel = new MapPanel();
//        add(mapPanel, BorderLayout.CENTER);
//
//        // Create the result text area
//        resultTextArea = new JTextArea(5, 20);
//        resultTextArea.setEditable(false);
//
//        // Add components to the JFrame
//        add(inputPanel, BorderLayout.WEST);
//        add(new JScrollPane(resultTextArea), BorderLayout.EAST);
//    }
//
//    private void showInputDialog() {
//        // Create a dialog to get input values
//        JDialog inputDialog = new JDialog(this, "Enter Station Codes", true);
//        inputDialog.setLayout(new FlowLayout());
//
//        JTextField stationCode1Field = new JTextField(10);
//        JTextField stationCode2Field = new JTextField(10);
//        JButton findButton = new JButton("Find");
//
//        findButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String stationCode1 = stationCode1Field.getText();
//                String stationCode2 = stationCode2Field.getText();
//                onFindShortestPathButtonClick(stationCode1, stationCode2);
//                inputDialog.dispose();
//            }
//        });
//
//        inputDialog.add(new JLabel("Enter Station Code 1:"));
//        inputDialog.add(stationCode1Field);
//        inputDialog.add(new JLabel("Enter Station Code 2:"));
//        inputDialog.add(stationCode2Field);
//        inputDialog.add(findButton);
//
//        // Set the size and show the dialog
//        inputDialog.setSize(300, 150);
//        inputDialog.setLocationRelativeTo(this);
//        inputDialog.setVisible(true);
//    }
//
//    private void onFindShortestPathButtonClick(String stationCode1, String stationCode2) {
//        // Call your Manager method to find the shortest path
//        manager.shortestPathPublic(stationCode1, stationCode2);
//
//        // Update the resultTextArea with the result
//        Station startStation = manager.getStationByCode(stationCode1);
//        Station endStation = manager.getStationByCode(stationCode2);
//
//        if (startStation != null && endStation != null) {
//            if (manager.getGraph().containsVertex(startStation) && manager.getGraph().containsVertex(endStation)) {
//                List<String> path = manager.findShortestPathDijkstra(startStation, endStation);
//                showResultDialog(startStation, endStation, path);
//            } else {
//                resultTextArea.setText("Start or end station not in the graph.");
//            }
//        } else {
//            resultTextArea.setText("Invalid station codes.");
//        }
//    }
//
//    private void showResultDialog(Station startStation, Station endStation, List<String> path) {
//        // Create a dialog to display the result
//        JDialog resultDialog = new JDialog(this, "Shortest Path Result", true);
//        resultDialog.setLayout(new BorderLayout());
//
//        JTextArea resultArea = new JTextArea();
//        resultArea.setEditable(false);
//        resultArea.append("Shortest path from " + startStation.getNameMedium() + " to " + endStation.getNameMedium() + ":\n");
//
//        for (String station : path) {
//            resultArea.append(station + "\n");
//        }
//
//        JScrollPane resultScrollPane = new JScrollPane(resultArea);
//        resultDialog.add(resultScrollPane, BorderLayout.CENTER);
//
//        JButton closeButton = new JButton("Close");
//        closeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resultDialog.dispose(); // Close the dialog
//            }
//        });
//
//        resultDialog.add(closeButton, BorderLayout.SOUTH);
//
//        // Set the size and show the dialog
//        resultDialog.setSize(300, 200);
//        resultDialog.setLocationRelativeTo(this);
//        resultDialog.setVisible(true);
//    }
//
//    private void displaySearchResult(List<String> searchResult) {
//        resultTextArea.setText("Search Result:\n");
//
//        if (searchResult.isEmpty()) {
//            resultTextArea.append("No matching stations found.");
//        } else {
//            for (String station : searchResult) {
//                resultTextArea.append(station.toString() + "\n");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                MyJFrame myJFrame = new MyJFrame();
//                myJFrame.setVisible(true);
//            }
//        });
//    }
//
//    private class MapPanel extends JPanel {
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//
//            // Draw the map
//            ImageIcon mapIcon = new ImageIcon("src/resources/908px-Netherlands_location_map.svg.png");
//            Image mapImage = mapIcon.getImage();
//            int imageWidth = mapIcon.getIconWidth();
//            int imageHeight = mapIcon.getIconHeight();
//
//            // Adjust the scaling factor and offset
//            double scaleFactor = Math.min(1.0, Math.min(500.0 / imageWidth, 500.0 / imageHeight));
//            int scaledWidth = (int) (imageWidth * scaleFactor);
//            int scaledHeight = (int) (imageHeight * scaleFactor);
//            int xOffset = (getWidth() - scaledWidth) / 2; // Center horizontally
//            int yOffset = (getHeight() - scaledHeight) / 2; // Center vertically
//
//            g.drawImage(mapImage, xOffset, yOffset, scaledWidth, scaledHeight, null);
//
//            // Draw stations
//            for (Station station : manager.getStations()) {
//                int x = mapLongitudeToX(station.getLongitude(), scaledWidth) + xOffset;
//                int y = mapLatitudeToY(station.getLatitude(), scaledHeight) + yOffset;
//
//                g.setColor(Color.RED);
//                g.fillOval(x - 5, y - 5, 10, 10);
//
//                // Debugging output
//                System.out.println("Station: " + station.getNameMedium() + " | X: " + x + " | Y: " + y);
//            }
//        }
//
//        private int mapLongitudeToX(double longitude, int scaledWidth) {
//            // Map longitude to X coordinate based on the width of the scaled map
//            return (int) ((longitude + 180) / 360 * scaledWidth);
//        }
//
//        private int mapLatitudeToY(double latitude, int scaledHeight) {
//            // Map latitude to Y coordinate based on the height of the scaled map
//            return (int) ((90 - latitude) / 180 * scaledHeight);
//        }
//    }
//}