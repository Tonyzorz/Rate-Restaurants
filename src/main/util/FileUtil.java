package main.util;

import main.dto.RestaurantDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FileUtil {

    File file = new File("src/main/db/list.txt");

    public boolean createFile() {

        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean doesFileExist() {
        return file.exists();
    }

    public String readFile() {

        boolean doesExist = doesFileExist();
        if (!doesExist) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        try {

            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void writeFile(ArrayList<RestaurantDTO> list) {

        Collections.sort(list);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");

            for (int i = 0; i < list.size(); i++) {
                RestaurantDTO restaurant = list.get(i);
                writer.print(i + 1 + "-");
                writer.print(restaurant.getStoreName() + "-");
                writer.print(restaurant.getMenu() + "-");
                writer.print(restaurant.getPrice() + "-");
                writer.print(restaurant.getRating() + "-");
                writer.print(restaurant.getInsertDate() + "-");
                writer.print(restaurant.getUpdateDate());
                writer.print("\n");
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
