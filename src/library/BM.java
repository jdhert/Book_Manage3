package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// BookManager를 구현하는 구현 객체
public class BM extends BookManager {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Book> bookList = new ArrayList<>();

    @Override
    void init() {
        bookList.add(new Book(1L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15")));
        bookList.add(new Book(2L,"K 배터리 레볼루션", "박순혁", Long.parseLong("9791191521221"), LocalDate.parse("2023-02-20")));
        bookList.add(new Book(3L, "위기의 역사", "오건영", Long.parseLong("9791169850360"), LocalDate.parse("2023-07-19")));
    }
    @Override
    void interactWithUser() {
        while (true) {
            System.out.println("■■■■■■ 도서 관리 프로그램 ■■■■■■");
            System.out.println("(1) 도서 조회");
            System.out.println("(2) 도서 등록");
            System.out.println("(3) 도서 수정");
            System.out.println("(4) 도서 삭제");
            System.out.println("(q) 프로그램 종료");
            System.out.print("선택 >> ");
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    // 조회
                    printAllBook();
                    break;
                case "2":
                    // 등록
                    addBook();
                    break;
                case "3":
                    // 수정
                    updateBook();
                    break;
                case "4":
                    // 삭제
                    removeBook();
                    break;
                case "q":
                    // 메소드를 종료
                    System.out.println("프로그램 종료!");
                    return;
                default:
                    System.out.println("보기에 나와있는 것을 입력하세요!!! :( ");
                    break;
            }

        }
    }

    public boolean idCheck(long id){
        for (Book b: bookList){
            if(b.getId() == id)
                return false;
        } return true;
    }


    @Override
    public void printAllBook() {
        System.out.println("■■■■■■■■ 도서 목록 조회 ■■■■■■■■");
        for (Book book : bookList) {
            System.out.print("[");
            System.out.print(book.getId());
            System.out.print(", ");
            System.out.print(book.getName());
            System.out.print(", ");
            System.out.print(book.getAuthor());
            System.out.print(", ");
            System.out.print(book.getIsbn());
            System.out.print(", ");
            System.out.print(book.getPublishedDate());
            System.out.print("]");
            System.out.println();
        }
    }

    public boolean checkBook(long id){

        for (Book b: bookList){
            if(b.getId() == id) {
                return true;
            }
        }
        System.out.println("해당 도서가 존재하지 않습니다!!! ");
        return false;

    }

    public void updateBook() {
        System.out.println("수정 메서드 실행");
        System.out.print("수정하고자 하는 도서번호를 입력하세요: ");
        long id = Long.parseLong(sc.nextLine());
        if(checkBook(id)) {
            System.out.println("[수정 정보를 입력해주세요]");
            System.out.print("제목: ");
            String name = sc.nextLine();
            System.out.print("저자: ");
            String author = sc.nextLine();
            System.out.print("isbn: ");
            String isbn = sc.nextLine();
            System.out.print("출판일(YYYY-MM-DD): ");
            String publishDate = sc.nextLine();
            for (Book b: bookList){
                if(b.getId().equals(id)) {
                    b.setName(name);
                    b.setAuthor(author);
                    b.setIsbn(Long.parseLong(isbn));
                    b.setPublishedDate(LocalDate.parse(publishDate));
                }
            }
            System.out.println("수정이 완료되었습니다.");
        }
    }


    public void removeBook() {
        boolean check = true;
        System.out.println("■■■■■■■■■■■ 도서 삭제 ■■■■■■■■■■■");
        System.out.println("삭제하고자 하는 도서의 도서번호를 입력하세요.");
        System.out.print("선택 >> ");
        long id = Long.parseLong(sc.nextLine());
        for (Book b: bookList) {
            if (id == b.getId()) {
                bookList.remove(b);
                System.out.println("삭제가 완료되었습니다.");
                check = false;
                break;
            }
        }
        if(check)
            System.out.println("해당 도서가 존재하지 않습니다.");
    }

    public void addBook() {
        System.out.println("■■■■■■■■■■■ 도서 등록 ■■■■■■■■■■■");
        // 1. 콘솔화면을 통해 사용자로부터 도서정보를 입력을 받는다.
        // id, 제목, 저자, isbn, 출판일 (5가지) (v)
        // 위의 정보로 책 객체를 생성한다. (v)
        // 2. 도서를 등록한다.
        // 사서를 통해 도서 저장 요청

        System.out.print("(1) 도서번호를 입력해주세요.(유일한 번호) >> ");
        String id = sc.nextLine();
        if(idCheck(Long.parseLong(id))) {
            System.out.print("(2) 도서명을 입력해주세요. >> ");
            String name = sc.nextLine();
            System.out.print("(3) 저자명을 입력해주세요. >> ");
            String author = sc.nextLine();
            System.out.print("(4) isbn을 입력해주세요. >> ");
            String isbn = sc.nextLine();
            System.out.print("(5) 출간일을 입력해주세요.(YYYY-MM-DD형식) >> ");
            String publishDate = sc.nextLine();

            // id, isbn는 String 타입이므로 Long으로 변환 후 매개값을 주어야한다.
            // publishedDate는 String 타입인데 LocalDate 타입으로 변환해주어야 한다. ==> "구글링"
            Book book = new Book(Long.parseLong(id),
                    name,
                    author,
                    Long.parseLong(isbn),
                    LocalDate.parse(publishDate));

            bookList.add(book);
            System.out.println("--- 도서 [" + book.getName() + "] 등록이 완료되었습니다. ---");
        } else System.out.println("ID값이 이미 존재합니다. ");
    }
}
