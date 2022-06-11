package main.service;

import main.dto.RestaurantDTO;
import main.util.FileUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class RateService{

    Scanner sc = new Scanner(System.in);
    ArrayList<RestaurantDTO> list = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();

    public void startProgram() {
        System.out.println("===================");
        System.out.println("| Rate Restaurant |");
        System.out.println("===================");

        System.out.println("1. 데이터 입력");
        System.out.println("2. 데이터 업데이트");
        System.out.println("3. 데이터 읽기");
        System.out.println("4. 데이터 삭제");
        System.out.println("0. 시스템 종료");

        System.out.println("0 ~ 4 입력해주세요");

        setData();

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                String userInput = getUserInput();
                insertData(userInput);

                fileUtil.writeFile(list);
                break;
            case 2:
                System.out.println("업데이트할 인덱스를 입력해주세요");
                choice = Integer.parseInt(sc.nextLine());
                userInput = getUserInput();
                updateData(userInput, choice);

                fileUtil.writeFile(list);
                break;
            case 3:
                System.out.println("0 = 모두 선택, 숫자 = 해당 인덱스 선택");
                choice = Integer.parseInt(sc.nextLine());

                if (choice > 0) {
                    selectOne(choice);
                } else {
                    selectAll();
                }

                break;
            case 4:
                System.out.println("0 = 모두 선택, 숫자 = 해당 인덱스 선택");
                choice = sc.nextInt();

                if (choice > 0) {
                    deleteData(choice);
                } else {
                    deleteAll();
                }

                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("0 ~ 4중 다시 입력해주세요");
                startProgram();
        }
    }

    public void setData() {
        String beforeData = fileUtil.readFile();

        for (String data : beforeData.split("\n")) {
            String[] info = data.split("-");
            if (null == info || info.length != 7) {
                continue;
            }

            String storeName = info[1];
            String menu = info[2];
            int price = Integer.parseInt(info[3]);
            float rating = Float.parseFloat(info[4]);
            String insertDate = info[5];
            String updateDate = info[6];

            list.add(new RestaurantDTO(storeName, menu, price, rating, insertDate, updateDate));
        }

        Collections.sort(list);
    }

    public String getUserInput() {
        StringBuilder sb = new StringBuilder();

        System.out.println("상호명 입력");
        String storeName = sc.nextLine();
        sb.append(storeName).append("-");

        System.out.println("대표메뉴 입력");
        String menu = sc.nextLine();
        sb.append(menu).append("-");

        System.out.println("가격 입력");
        String price = sc.nextLine();
        sb.append(price).append("-");

        System.out.println("평점 입력");
        String rating = sc.nextLine();
        sb.append(rating).append("-");

        return sb.toString();
    }

    public void insertData(String data) {
        String[] infos = data.split("-");

        String storeName = infos[0];
        String menu = infos[1];
        int price = Integer.parseInt(infos[2]);
        float rating = Float.parseFloat(infos[3]);
        String insertDate = getDate();

        list.add(new RestaurantDTO(storeName, menu, price, rating, insertDate, insertDate));
    }

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public void updateData(String data, int index) {
        RestaurantDTO restaurantDTO = list.get(index - 1);

        String[] infos = data.split("-");

        String storeName = infos[0];
        String menu = infos[1];
        int price = Integer.parseInt(infos[2]);
        float rating = Float.parseFloat(infos[3]);
        String insertDate = restaurantDTO.getInsertDate();
        String updateDate = getDate();

        list.set(index -1, new RestaurantDTO(storeName, menu, price, rating, insertDate, updateDate));
    }

    public void selectAll() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public void selectOne(int index) {
        System.out.println(list.get(index - 1).toString());
    }

    public void deleteData(int index) {
        list.remove(index - 1);
        fileUtil.writeFile(list);
    }

    public void deleteAll() {
        list.clear();
        fileUtil.writeFile(list);
    }
}
