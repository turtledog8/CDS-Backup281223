import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        Manager2 manager2 = new Manager2();
        manager2.initialise();

        // an argument (console, gui or both) skips the question
        String mode = args.length > 0 ? args[0].toLowerCase() : "";
        while (!isValidMode(mode)) {
            System.out.println("""

                    Start in:
                    1- Console
                    2- Window (map)
                    3- Both
                    """);
            System.out.print("Choice: ");
            mode = manager2.scanner.nextLine().trim().toLowerCase();
        }

        boolean console = mode.equals("1") || mode.equals("console") || mode.equals("3") || mode.equals("both");
        boolean gui = mode.equals("2") || mode.equals("gui") || mode.equals("3") || mode.equals("both");

        if (gui) {
            // the window runs on its own thread, so the console menu below can run at the same time
            SwingUtilities.invokeLater(() -> new MyJFrame(manager2).setVisible(true));
        }

        if (console) {
            manager2.setMenuChoice();
        }
    }

    private static boolean isValidMode(String mode) {
        return mode.equals("1") || mode.equals("2") || mode.equals("3")
                || mode.equals("console") || mode.equals("gui") || mode.equals("both");
    }
}
