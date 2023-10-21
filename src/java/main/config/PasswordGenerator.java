package MayBeNeedful;
import java.util.Random;
import java.util.Scanner;
public class PasswordGenerator {
    private int structure;
    private int length;
    private String generating(){
        String pass = "";
        int tmp = 0;
        char[] digits = new char[10];
        char[] letters = new char[52];
        Random random = new Random();

        // Заполнение массивов чисел и букв символами
        for (int i = 0; i < digits.length; i++) digits[i] = (char) (48 + i);
        for (int g = 0; g < letters.length-26; g++) letters[g] = (char) (65 + g);
        for (int k = 26; k < letters.length; k++) letters[k] = (char) (97 + k - 26);

        // генерация пароля в зависимости от выбранной структуры пользователя
        if (this.structure == 1){ // only letters
            for (int i = 0; i < this.length; i++) pass += letters[random.nextInt(0, letters.length)];
        }
        else if (this.structure == 2){ // only digits
            for (int i = 0; i < this.length; i++) pass += digits[random.nextInt(0, digits.length)];
        }
        else if (this.structure == 3){ // both of them
            for (int i = 0; i < this.length; i++){
                if (!random.nextBoolean()) pass += digits[random.nextInt(0, digits.length)];
                else pass += letters[random.nextInt(0, letters.length)];
            }
        }

        return pass;
    }
    public void menu(){
        PasswordGenerator generator = new PasswordGenerator();
        Scanner scan = new Scanner(System.in);
        String password;
        byte way = 0;
        while (true) {
            System.out.print("Введите желаемую длину пароля (не менее 8 символов, для вашей безопасности): ");
            generator.length = scan.nextInt();
            if (generator.length < 8) {
                System.out.println("\nДлина пароля изменена на 8, ибо мы желаем лучшего для Вас!\n");
                generator.length = 8;
            }

            while (true) {
                System.out.print("1) Только буквы\n2) Только цифры\n3) Цифры и буквы\nВыберите желаемую структуру пароля: ");
                generator.structure = scan.nextInt();

                if (generator.structure == 1 || generator.structure == 2 || generator.structure == 3)
                    break;
            }

            password = generator.generating();
            System.out.println("\nВаш сгенерированный пароль: " + password);

            System.out.print("\n1) Продолжить\n0) Выход\nВыберите операцию: ");
            way = scan.nextByte();
            if (way == 0) break;
            System.out.println();
        }

        scan.close();
    }
}
