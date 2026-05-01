import java.io.*;

class Coordinator {
    static boolean first_entry = true;
    int converter(char character) {
        switch (character) {
            case 'q':
                return 0;
            case 'w':
                return 1;
            case 'e':
                return 2;
            case 'r':
                return 3;
            case 't':
                return 4;
            case 'y':
                return 5;
            case 'u':
                return 6;
            case 'i':
                return 7;
            case 'o':
                return 8;
            case 'p':
                return 9;
            case 'a':
                return 10;
            case 's':
                return 11;
            case 'd':
                return 12;
            case 'f':
                return 13;
            case 'g':
                return 14;
            case 'h':
                return 15;
            case 'j':
                return 16;
            case 'k':
                return 17;
            case 'l':
                return 18;
            case 'z':
                return 19;
            case 'x':
                return 20;
            case 'c':
                return 21;
            case 'v':
                return 22;
            case 'b':
                return 23;
            case 'n':
                return 24;
            case 'm':
                return 25;
        }

        return 0;
    }

    void processor(String string) {
        if (string.equals(DotManipulation.exit_keys)) {
            DotManipulation.operation = false;
            return;
        }
        
        int[] characters = new int[string.length()];

        for (int x = 0; x < characters.length; x++)
            characters[x] = converter(string.charAt(x));
        for (int number : characters) {
            DotManipulation.keys[number] = !DotManipulation.keys[number];
        }

        Update(string);
    }

    void Update(String string) {
        String file_name = "";

        if (first_entry) {
            first_entry = false;
            try (BufferedReader br = new BufferedReader(new FileReader("index.txt"))) {
                String test_string = br.readLine();
                if (test_string == null) {
                    try (FileWriter fw = new FileWriter("index.txt")) {
                        fw.write("1");
                    } catch (IOException exc) {}
                } else {
                    try (FileWriter fw = new FileWriter("index.txt")) {
                        fw.write(String.valueOf(Integer.valueOf(test_string)+1));
                    } catch (IOException exc) {}
                }
            } catch (FileNotFoundException exc) {
                    try (FileWriter fw = new FileWriter("index.txt")) {
                        fw.write("1");
                    } catch (IOException exc1) {}
            } catch (IOException exc) {}

            try (BufferedReader br = new BufferedReader(new FileReader("index.txt"))) {
                file_name = br.readLine() + ".txt";
            } catch (IOException exc) {}

            try (FileWriter fw = new FileWriter(file_name)) {
                fw.write(String.valueOf(1));
                fw.write("\n"+string);
            } catch (IOException exc) {}
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader("index.txt"))) {
                file_name = br.readLine() + ".txt";
            } catch (IOException exc) {}

            int limit = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {
                limit = Integer.valueOf(br.readLine());
            } catch (IOException exc) {}

            String[] keys = new String[limit];

            try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {
                br.readLine();
                for (int x = 0; x < limit; x++)
                    keys[x] = br.readLine();
            } catch (IOException exc) {}

            try (FileWriter fw = new FileWriter(file_name)) {
                fw.write(String.valueOf(limit+1));
                for (int x = 0; x < keys.length; x++)
                    fw.write("\n"+keys[x]);
                fw.write("\n"+string);
            } catch (IOException exc) {}
        }
    }

    boolean Input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, System.console().charset()));

        try {
            System.out.print("Dot Manipulation: ");
            processor(br.readLine());
        } catch (IOException exc) {}

        System.out.println();

        return true;
    }

    boolean Output() {
        for (int x = 0; x < 26; x++) {
            if (x == 10 || x == 19)
                System.out.println();
                
            if (DotManipulation.keys[x] == true)
                System.out.print(".");
            else
                System.out.print(" ");

            if (x == 25)
                System.out.println("\n");
        }

        return true;
    }

    synchronized void Method(String name) {
        while (DotManipulation.operation) {
            if (name.equals("Output"))
                if (Output()) {
                    notify();
                    try {
                        wait();
                    } catch (InterruptedException exc) {}
                }
            
            if (name.equals("Input"))
                if (Input()) {
                    notify();
                    try {
                        if (DotManipulation.operation == true)
                            wait();
                    } catch (InterruptedException exc) {}
                }
        }
    }
}

class IO extends Thread {
    String name = "";
    Coordinator ob;

    IO (String name, Coordinator ob) {
        this.name = name;
        this.ob = ob;
    }

    public void run() {
        ob.Method(name);
    }
}

class ExtravagantDisplay extends Thread {
    boolean[] keys = new boolean[26];

    int converter(char character) {
        switch (character) {
            case 'q':
                return 0;
            case 'w':
                return 1;
            case 'e':
                return 2;
            case 'r':
                return 3;
            case 't':
                return 4;
            case 'y':
                return 5;
            case 'u':
                return 6;
            case 'i':
                return 7;
            case 'o':
                return 8;
            case 'p':
                return 9;
            case 'a':
                return 10;
            case 's':
                return 11;
            case 'd':
                return 12;
            case 'f':
                return 13;
            case 'g':
                return 14;
            case 'h':
                return 15;
            case 'j':
                return 16;
            case 'k':
                return 17;
            case 'l':
                return 18;
            case 'z':
                return 19;
            case 'x':
                return 20;
            case 'c':
                return 21;
            case 'v':
                return 22;
            case 'b':
                return 23;
            case 'n':
                return 24;
            case 'm':
                return 25;
        }

        return 0;
    }

    void processor(String string) {
        int[] characters = new int[string.length()];

        for (int x = 0; x < characters.length; x++)
            characters[x] = converter(string.charAt(x));
        for (int number : characters) {
            keys[number] = !keys[number];
        }
    }

    void ShowDots(String argument) {
        processor(argument);
        
        for (int x = 0; x < 10; x++)
            if (keys[x] == true)
                System.out.print(".");
            else
                System.out.print(" ");

        try {
            sleep(385);
        } catch (Throwable exc) {}

        System.out.println();

        for (int x = 10; x < 19; x++)
            if (keys[x] == true)
                System.out.print(".");
            else
                System.out.print(" ");

        try {
            sleep(385);
        } catch (Throwable exc) {}

        System.out.println();

        for (int x = 19; x < 26; x++)
            if (keys[x] == true)
                System.out.print(".");
            else
                System.out.print(" ");
        
        try {
            sleep(385);
        } catch (Throwable exc) {}

        System.out.println("\n");

    }

    void Output(String arguments[][]) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 26; y++)
                keys[y] = true;

            for (int y = 0; y < arguments[x].length; y++) {
                ShowDots(arguments[x][y]);
            }
        }
    }

    String RetrieveFileName(String arg) {
        return arg + ".txt";
    }

    void ArrangeFiles() {
        int limit_2 = 0;
        int limit_3 = 0;
        try (BufferedReader br2 = new BufferedReader(new FileReader("2.txt"))) {
            try (BufferedReader br3 = new BufferedReader(new FileReader("3.txt"))) {
                String[] file_2 = new String[Integer.valueOf(br2.readLine())];
                String[] file_3 = new String[Integer.valueOf(br3.readLine())];

                for (int x = 0; x < file_2.length; x++)
                    file_2[x] = br2.readLine();
                
                for (int x = 0; x < file_3.length; x++)
                    file_3[x] = br3.readLine();

                try (FileWriter fw = new FileWriter("index.txt")) {
                } catch (IOException exc) {}
            } catch (IOException exc) {}
        } catch (IOException exc) {}
    }

    public void run() {
        String[][] affected_keys = new String[3][];

        try (BufferedReader br = new BufferedReader(new FileReader("index.txt"))) {
            String size = br.readLine();
            if (size == null || Integer.valueOf(size) < 3)
                return;
        } catch (FileNotFoundException exc) {
            return;
        } catch (IOException exc) {}

        System.out.println("Your choices in the previous three sessions: \n");

        for (int x = 0; x < 3; x++) {
            try (BufferedReader br = new BufferedReader(new FileReader(RetrieveFileName(String.valueOf(x+1))))) {
                int size = Integer.valueOf(br.readLine());
                affected_keys[x] = new String[size];
                for (int y = 0; y < size; y++) {
                    affected_keys[x][y] = br.readLine();
                }
            } catch (IOException exc) {}
        }

        Output(affected_keys);
        ArrangeFiles();
    }
}

class DotManipulation {
    static boolean[] keys = new boolean[26];
    static boolean operation = true;
    static String exit_keys = "";

    public static void main(String[] args) {
        for (int x = 0; x < 26; x++)
            keys[x] = true;

        Coordinator ob = new Coordinator();

        ExtravagantDisplay ED = new ExtravagantDisplay();
        ED.start();
        try {
            ED.join();
        } catch (InterruptedException exc) {}

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, System.console().charset()));
        try {
            System.out.print("Enter the exit keys: ");
            exit_keys = br.readLine();
            System.out.println();
        } catch (IOException exc) {}

        IO Output = new IO("Output", ob);
        IO Input = new IO("Input", ob);

        Output.start();
        Input.start();
    }
}