package UIMenu.Interface;
import UIMenu.ItemMenu;

import java.util.Arrays;
import java.util.Scanner;

public interface IMenuOptions {
    public static String showUiMenuItems(ItemMenu[] listItems) {
        for (ItemMenu listItem : listItems) {
            System.out.println(listItem.getId() + " - " + listItem.getItemName());
        }

        return catchUserInputValue();
    }

    public static ItemMenu getItemOptionById(ItemMenu[] listItems, int id) {
        return Arrays.stream(listItems).filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public static String catchUserInputValue() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
