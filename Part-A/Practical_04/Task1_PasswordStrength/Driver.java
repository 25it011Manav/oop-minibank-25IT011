public class Driver {
    static String strength(String s) {
        int n = (s.length() >= 8 ? 1 : 0) + (s.matches(".*[A-Z].*") ? 1 : 0) + (s.matches(".*\\d.*") ? 1 : 0)
                + (s.matches(".*[^A-Za-z0-9].*") ? 1 : 0);
        return n < 2 ? "Weak" : n < 4 ? "Medium" : "Strong";
    }

    public static void main(String[] a) {
        for (String p : new String[] { "abc", "Abc123", "Harsh123", "48@48@48@", "Abcd1234!", "Password@742" })
            System.out.println("Password: " + p + "\nStrength: " + strength(p) + "\n");
    }
}
