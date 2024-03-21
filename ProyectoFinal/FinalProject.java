import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalProject {
    private static Scanner scanner = new Scanner(System.in);
    private static final int ID_TYPE = 0;
    private static final int ID_NUMBER = 1;
    private static final int FIRST_NAME = 2;
    private static final int EMAIL = 6;
    private static final int PASSWORD = 8;

    private static List<String[]> users = new ArrayList<>();

    public static void showLoginRegistrationMenu() {
        System.out.println("Bienvenido a MyHotel ...");
        System.out.println("Mas que un lugar para descansar.");
        System.out.println("----------------------------------------------");
        System.out.println("1. Registrarse como cliente.");
        System.out.println("2. Iniciar Sesión.");
        System.out.println("3. Salir");

        int option = readOption();

        switch (option) {
            case 1:
                requestRegistrationData();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Adios!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida");
        }
        showLoginRegistrationMenu();
    }

    public static int readOption() {
        while (true) {
            try {
                System.out.println("Ingrese la opción deseada:");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese una opción valida (1, 2, or 3):");
                scanner.next(); 
            }
        }
    }

    public static void requestRegistrationData() {
        System.out.println("Ingrese los siguientes datos:");

        scanner.nextLine();

        String idType = "";
        while (!isValidIdType(idType)) {
            System.out.print("Tipo de identificación (CC, TI, PA): ");
            idType = scanner.nextLine().toUpperCase();
            if (!isValidIdType(idType)) {
                System.out.println("Invalido. Por favor ingrese CC, TI, or PA.");
            }
        }

        String idNumber = "";
        while (!isValidIdNumber(idNumber)) {
            System.out.print("Documento de identificación: ");
            idNumber = scanner.nextLine();
            if (!isValidIdNumber(idNumber)) {
                System.out.println("Invalido. Por favor ingrese un numero valido.");
            }
        }

        System.out.print("Nombres: ");
        String firstName = scanner.nextLine();

        System.out.print("Apellidos: ");
        String lastName = scanner.nextLine();

        String email = "";
        while (!isValidEmail(email)) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Formato de email invalido.");
            }
        }

        System.out.print("Dirección de residencia: ");
        String residentialAddress = scanner.nextLine();

        System.out.print("Ciudad de residencia: ");
        String cityOfResidence = scanner.nextLine();

        System.out.print("Teléfono de contacto: ");
        String contactPhoneNumber = scanner.nextLine();

        String password = "0";
        String confirmPassword = "1";
        while (!password.equals(confirmPassword)) {
            System.out.print("Contraseña: ");
            password = scanner.nextLine();

            System.out.print("Confirmar Contraseña: ");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Las contraseñas no coinciden, intente de nuevo.");
            }
        }

        registerUser(idType, idNumber, firstName, lastName, email, residentialAddress, cityOfResidence, contactPhoneNumber, password);
    }

    public static boolean isValidIdType(String idType) {
        return idType.equals("CC") || idType.equals("TI") || idType.equals("PA");
    }

    public static boolean isValidIdNumber(String idNumber) {
        
        return idNumber.matches("\\d+");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void registerUser(String idType, String idNumber, String firstName, String lastName, String email, String residentialAddress, String cityOfResidence, String contactPhoneNumber, String password) {
        String[] user = new String[10];
        user[ID_TYPE] = idType;
        user[ID_NUMBER] = idNumber;
        user[FIRST_NAME] = firstName;
        user[EMAIL] = email;
        user[PASSWORD] = password;

        users.add(user);
        System.out.println("Usuario registrado correctamente.");
    }

    public static void login() {
        scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (validateCredentials(email, password)) {
            System.out.println("Usuario logueado correctamente.");
        } else {
            System.out.println("Usuario incorrecto, intente una vez más.");
        }
    }

    public static boolean validateCredentials(String email, String password) {
        for (String[] user : users) {
            if (user != null && user[EMAIL] != null && user[PASSWORD] != null && user[EMAIL].equals(email) && user[PASSWORD].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        showLoginRegistrationMenu();
    }
}
