import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


public class LoginWindow extends JFrame {
	
	private JLabel lblMsg, lblID, lblPassword;
	private JTextField txtId;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;
	
	public LoginWindow() {
		setTitle("로그인 화면");
		setSize(200, 170);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		
		lblMsg = new JLabel("  아이디와 비밀번호를 입력하세요   ");
		//보더 생성 
		EtchedBorder eBorder = new EtchedBorder();
		lblMsg.setBorder(eBorder);
		mainPanel.add(lblMsg);
		
		//아이디 입력 부분 
		lblID = new JLabel("ID");
		mainPanel.add(lblID);
		txtId = new JTextField(13);
		mainPanel.add(txtId);
		
		//비밀번호 입력 부분 
		lblPassword = new JLabel("PW");
		mainPanel.add(lblPassword);
		txtPassword = new JPasswordField(13);
		mainPanel.add(txtPassword);
		
		//버튼 배치 
		btnLogin = new JButton(" 로그인 ");
		mainPanel.add(btnLogin);
		btnRegister = new JButton("회원가입");
		mainPanel.add(btnRegister);
		
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.printf("버튼 누름\n");
				//어떤 버튼이 눌렸는지 구분해야 함 
				if(e.getSource() == btnLogin) {
					//System.out.printf("로그인 버튼 누름\n");
					
					//입력한 내용 가져오기 
					String id = txtId.getText();
					char[] password = txtPassword.getPassword();
					//배열은 toString이 재정의 되어있지 않아서 해시코드 출력됨
					//문자 배열을 가지고 String 생성
					//값의 동일성을 확인할 때 배열은 모든 요소를 하나씩 비교 
					//String은 equals 메소드를 이용하면 되므로 편리함 
					String pw = new String(password);
					
					//System.out.printf("입력한 아이디:%s\n", id);
					//System.out.printf("입력한 비밀번호:%s\n", password);

					//id의 좌우공백을 제거하고 모두 대문자로 변경 
					id = id.trim().toUpperCase();
					if(id.length() < 1) {
						lblMsg.setText(" 아이디는 필수 입력입니다 ");
						lblMsg.setBackground(Color.RED);
						//유효성 겁사에 걸리면 더이상 진행하지 않고 중단
						return; 
					}
					else if(password.length < 1) {
						lblMsg.setText("비밀번호는 필수 입력입니다");
						lblMsg.setBackground(Color.RED);
						return; 
					}
					
					//id가 ROOT면 비밀번호 체크 
					//ROOT가 아니면 없는 아이디라고 출력 
					if(id.equals("ROOT")){
						lblMsg.setText(" 존재하지 않는 아이디 입니다 ");
						lblMsg.setBackground(Color.RED);
					}
					else if(!pw.equals("1234")) {
						lblMsg.setText("   잘못된 비밀번호 입니다   ");
						lblMsg.setBackground(Color.RED);
					}
					else {
						lblMsg.setText("   로그인에 성공하셨습니다   ");
						lblMsg.setBackground(Color.GREEN);
					}

				}
				else if(e.getSource() == btnRegister) {
					//System.out.printf("회원가입 버튼 누름\n");
					//setVisible(false); //자신을 보이지 않게 하기 
					//새로운 윈도우 출력 
					new ButtonWindow();
				}
					
			}
			
		};
		//2개 컴포넌트의 이벤트를 하나의 객체로 처리하는 것 : 이벤트 라우팅 
		btnLogin.addActionListener(action);
		btnRegister.addActionListener(action);
		
		
		setResizable(false);
		
		add(mainPanel);
		setVisible(true);
	}

}
