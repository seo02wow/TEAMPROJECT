package Join_Membership;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mystudy.cafetestcommon.CommonJDBCUtil;

public class Join {
    public static void main(String[] args) {
        try (Connection conn = CommonJDBCUtil.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. 주문하기");
                System.out.println("2. 회원가입");
                System.out.println("3. 회원조회");
                System.out.println("4. 종료");
                System.out.print("메뉴를 선택하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 입력 버퍼 비우기

                switch (choice) {
                    case 1:
                        // 메뉴 보기, 주문 하기 기능 추가
                        break;
                    case 2:
                        performSignUp(conn, scanner);
                        break;
                    case 3:
                        retrieveAndUpdateUserInfo(conn, scanner);
                        break;
                    case 4:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("올바른 메뉴를 선택하세요.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static void performSignUp(Connection connection, Scanner scanner) {
		String custname, password, phone;
    
    try {
        String getSeqQuery = "SELECT CUSTID_SEQ.NEXTVAL FROM DUAL";
        try (PreparedStatement seqStatement = connection.prepareStatement(getSeqQuery);
             ResultSet resultSet = seqStatement.executeQuery()) {
            if (resultSet.next()) {
                int custid = resultSet.getInt(1);

                System.out.println("고객 고유 번호: " + custid);
                
                System.out.print("이름을 입력하세요 : ");
                custname = scanner.nextLine();

                System.out.print("비밀번호를 입력하세요 : ");
                password = scanner.nextLine();

                System.out.print("핸드폰 번호를 입력하세요 : ");
                phone = scanner.nextLine();

                saveToDatabase(connection, custid, custname, phone, password);
                addStamp(connection, custid);
                System.out.println("회원가입이 완료되었습니다.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
	public static void addStamp(Connection connection, int custid) { // 회원 가입 시 CUSTOMER 테이블 CUSTID 그리고 STAMP 테이블 STAMPID, CUSTID에 1을 자동으로 넣고 회원가입할때마다 1증가
	    try {
	        String insertQuery = "INSERT INTO STAMP (STAMPID, STAMPCNT, CUSTID) VALUES (?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	        	int i = 1;
	            preparedStatement.setInt(i++, custid); // 스탬프 테이블의 STAMPID 컬럼에 CUSTID 값을 삽입
	            preparedStatement.setInt(i++, 0); 
	            preparedStatement.setInt(i++, custid);// 스탬프 테이블의 CUSTID 컬럼에 CUSTID 값을 삽입
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	    
	public static void saveToDatabase(Connection connection, int id, String name, String password, String phoneNumber) {
        try {
            String insertQuery = "INSERT INTO CUSTOMER (CUSTID, CUSTNAME, PASSWORD, PHONE) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(4, password);
                preparedStatement.setString(3, phoneNumber);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveAndUpdateUserInfo(Connection connection, Scanner scanner) { // 회원 조회 (고유 번호 입력) 그리고 1.수정, 2.탈퇴, 3.복구, 4.이전으로
        while (true) {
            System.out.print("본인의 고객 고유 번호를 입력하세요 (0을 입력하면 처음으로 돌아갑니다): ");
            int custid = scanner.nextInt();
            scanner.nextLine(); // 입력 버퍼 비우기

            if (custid == 0) {
                System.out.println("처음으로 돌아갑니다.");
                break;
            }

            String selectQuery = "SELECT CUSTNAME, PASSWORD, PHONE, STATUS FROM CUSTOMER WHERE CUSTID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, custid);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String custname = resultSet.getString("CUSTNAME");
                    String password = resultSet.getString("PASSWORD");
                    String phone = resultSet.getString("PHONE");
                    String status = resultSet.getString("STATUS");

                    System.out.println("회원 정보");
                    System.out.println("고객 고유 번호 : " + custid);
                    System.out.println("이름 : " + custname);
                    System.out.println("비밀번호 : " + password);
                    System.out.println("핸드폰 번호 : " + phone);
                    System.out.println("계정 상태 : " + status);

                    System.out.println("1. 정보 수정");
                    System.out.println("2. 회원 탈퇴");
                    System.out.println("3. 회원 복구");
                    System.out.println("4. 이전으로");
                    System.out.print("원하는 작업을 선택하세요: ");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // 입력 버퍼 비우기

                    switch (userChoice) {
                        case 1:
                            updateUserInfo(connection, custid, scanner);
                            break;
                        case 2:
                            deactivateUser(connection, custid);
                            break;
                        case 3:
                        	activateUser(connection, custid);
                        	break;
                        case 4:
                            System.out.println("처음으로 돌아갑니다.");
                            break;
                        default:
                            System.out.println("올바른 작업을 선택하세요.");
                    }
                } else {
                    System.out.println("해당 번호로 회원 정보를 찾을 수 없습니다.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public static void updateUserInfo(Connection connection, int custid, Scanner scanner) { // 회원 수정 이름, 패스워드, 핸드폰 번호(업데이트)
	    try {
	        System.out.print("새로운 이름을 입력하세요: ");
	        String newName = scanner.nextLine();

	        System.out.print("새로운 비밀번호를 입력하세요: ");
	        String newPassword = scanner.nextLine();

	        System.out.print("새로운 핸드폰 번호를 입력하세요: ");
	        String newPhone = scanner.nextLine();

	        String updateQuery = "UPDATE CUSTOMER SET CUSTNAME = ?, PASSWORD = ?, PHONE = ? WHERE CUSTID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setString(1, newName);
	            preparedStatement.setString(2, newPassword);
	            preparedStatement.setString(3, newPhone);
	            preparedStatement.setInt(4, custid);

	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void deactivateUser(Connection connection, int custid) { // 회원 비활성화 (STATUS 컬럼 추가해 기본값 'ACTIVE')
	    try {
	        String updateQuery = "UPDATE CUSTOMER SET STATUS = 'INACTIVE' WHERE CUSTID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setInt(1, custid);

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("회원 정보가 비활성화되었습니다.");
	            } 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void activateUser(Connection connection, int custid) { // 회원 활성화 (INACTIVE 를 ACTIVE로 상태변경)
	    try {
	        String updateQuery = "UPDATE CUSTOMER SET STATUS = 'ACTIVE' WHERE CUSTID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setInt(1, custid);

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("회원 정보가 활성화되었습니다.");
	            } 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
