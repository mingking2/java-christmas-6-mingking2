package christmas.model;


public enum Menu {

    양송이수프(MenuType.APPETIZER, "에피타이저", "양송이수프", 6_000),
    타파스(MenuType.APPETIZER, "에피타이저", "타파스", 5_500),
    시저샐러드(MenuType.APPETIZER, "에피타이저", "시저샐러드", 8_000),

    티본스테이크(MenuType.MAIN, "메인", "티본스테이크", 55_000),
    바비큐립(MenuType.MAIN, "메인", "바비큐립", 54_000),
    해산물파스타(MenuType.MAIN, "메인", "해산물파스타", 35_000),
    크리스마스파스타(MenuType.MAIN, "메인", "크리스마스파스타", 25_000),

    초코케이크(MenuType.DESSERT, "디저트", "초코케이크", 15_000),
    아이스크림(MenuType.DESSERT, "디저트", "아이스크림", 5_000),

    제로콜라(MenuType.BEVERAGE, "음료", "제로콜라", 3_000),
    레드와인(MenuType.BEVERAGE, "음료", "레드와인", 60_000),
    샴페인(MenuType.BEVERAGE, "음료", "샴페인", 25_000);

    private final MenuType menuType;
    private final String menuTitle;
    private final String menuName;
    private final int menuPrice;

    Menu(MenuType menuType, String menuTitle, String menuName, int menuPrice) {
        this.menuType = menuType;
        this.menuTitle = menuTitle;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }


}
