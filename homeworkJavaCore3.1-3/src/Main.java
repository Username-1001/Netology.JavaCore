import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> savePaths = new ArrayList<>();
        install();

        GameProgress[] games = new GameProgress[]{
                new GameProgress(100, 1, 1, 2),
                new GameProgress(80, 2, 3, 15),
                new GameProgress(75, 4, 10, 50)};

        for (int i = 0; i < games.length; i++) {
            String path = "Games//savegames//save" + i + ".dat";
            if (saveGame(path, games[i])) {
                savePaths.add(path);
            }
        }

        zipFiles("Games//savegames//saves.zip", savePaths);

        File saveGames = new File("Games//savegames");
        Arrays.stream(saveGames.listFiles()).forEach(System.out::println);

        openZip("Games//savegames//saves.zip", "Games//savegames");
        GameProgress savedGameProgress = openProgress("Games//savegames//save1.dat");
        System.out.println(savedGameProgress);
    }

    private static GameProgress openProgress(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void openZip(String zipPath, String targetPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;

            while ((entry = zis.getNextEntry()) != null ) {
                name = entry.getName();

                try (BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(targetPath + "//" + name))) {
                    bis.write(zis.readAllBytes());
                    bis.flush();
                    zis.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void install() {
        Log log = new Log();

        if (new File("Games").mkdir()) {
            log.addMessageToLog("каталог \"Games\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\"");
        }

        if (new File("Games//src").mkdir()) {
            log.addMessageToLog("каталог \"Games\\src\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\src\"");
        }

        if (new File("Games//res").mkdir()) {
            log.addMessageToLog("каталог \"Games\\res\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\res\"");
        }

        if (new File("Games//savegames").mkdir()) {
            log.addMessageToLog("каталог \"Games\\savegames\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\savegames\"");
        }

        if (new File("Games//temp").mkdir()) {
            log.addMessageToLog("каталог \"Games\\temp\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\temp\"");
        }


        if (new File("Games//src//main").mkdir()) {
            log.addMessageToLog("каталог \"Games\\src\\main\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\src\\main\"");
        }

        if (new File("Games//src//test").mkdir()) {
            log.addMessageToLog("каталог \"Games\\src\\test\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\src\\test\"");
        }

        if (new File("Games//res//drawables").mkdir()) {
            log.addMessageToLog("каталог \"Games\\res\\drawables\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\res\\drawables\"");
        }

        if (new File("Games//res//vectors").mkdir()) {
            log.addMessageToLog("каталог \"Games\\res\\vectors\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\res\\vectors\"");
        }

        if (new File("Games//res//icons").mkdir()) {
            log.addMessageToLog("каталог \"Games\\res\\icons\" успешно создан");
        } else {
            log.addMessageToLog("не удалось создать каталог \"Games\\res\\icons\"");
        }

        try {
            if (new File("Games//src//main", "Main.java").createNewFile()) {
                log.addMessageToLog("файл Main.java успешно создан");
            } else {
                log.addMessageToLog("не удалось создать файл Main.java");
            }
        } catch (IOException ex) {
            log.addMessageToLog(ex.getMessage());
        }

        try {
            if (new File("Games//src//main", "Utils.java").createNewFile()) {
                log.addMessageToLog("файл Utils.java успешно создан");
            } else {
                log.addMessageToLog("не удалось создать файл Utils.java");
            }
        } catch (IOException ex) {
            log.addMessageToLog(ex.getMessage());
        }

        try {
            if (new File("Games//temp", "temp.txt").createNewFile()) {
                log.addMessageToLog("файл temp.txt успешно создан");
            } else {
                log.addMessageToLog("не удалось создать файл temp.txt");
            }
        } catch (IOException ex) {
            log.addMessageToLog(ex.getMessage());
        }

        log.save("Games//temp//temp.txt");
    }

    public static boolean saveGame(String path, GameProgress gameProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(gameProgress);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void zipFiles(String zipPath, List<String> savePaths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String file : savePaths) {
                File fileToDelete = new File(file);

                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(fileToDelete.getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();

                    fis.close();
                    fileToDelete.delete();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}