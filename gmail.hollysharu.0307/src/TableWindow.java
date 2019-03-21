import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TableWindow extends JFrame{
	
	//데이터를 추가&편집 할 수 있도록 벡터를 선언 
	//칼럼 이름을 저장할 벡터 
	Vector<String> title;
	//데이터 하나 하나를 저장할 벡터 
	Vector<Vector<String>> data;
	
	//데이터를 행과 열 단위로 출력할 테이블 변수 
	JTable table; 
	
	JLabel lblName, lblNation;
	JTextField txtName, txtNation;
	JButton btnInsert, btnRemove;
	
	public TableWindow() {
		setTitle("테이블 사용");
		setLocation(100, 100);
		setSize(500, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		title = new Vector<String>();
		//칼럼 이름 추가
		title.add("도시");
		title.add("국가");

		data = new Vector<Vector<String>>();
	
		//데이터 추가 
		Vector <String> imsi = new Vector<String>(); 
		imsi.add("아를");
		imsi.add("프랑스");
		data.add(imsi);
		
		imsi = new Vector<String>(); // 새로운 인스턴스 
		imsi.add("피렌체");
		imsi.add("이탈리아");
		data.add(imsi);
		
		imsi = new Vector<String>(); // 또 다른 인스턴스 
		imsi.add("런던");
		imsi.add("영국");
		data.add(imsi);
		
		//테이블에 연결될 데이터 모델 생성
		DefaultTableModel model = new DefaultTableModel(data, title);
		//테이블을 이용해서 테이블 생성 
		table = new JTable(model);
	
		//테이블을 바로 추가 - 컬럼 이름 출력 안 됨
		//add(table);
		
		//JScrollPane 위에 부착해서 출력 
		JScrollPane sp = new JScrollPane(table);
		add(sp);
		
		JPanel panel = new JPanel();
		lblName = new JLabel("도시");
		panel.add(lblName);
		txtName = new JTextField(10);
		panel.add(txtName);
		lblNation = new JLabel("국가");
		panel.add(lblNation);
		txtNation = new JTextField(10);
		panel.add(txtNation);
		
		btnInsert = new JButton("추가");
		panel.add(btnInsert);
		btnRemove = new JButton("삭제");
		panel.add(btnRemove);
		
		//버튼의 클릭 이벤트 처리를 위한 객체
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnInsert) {
					//System.out.printf("삽입 버튼\n");
					
					//txtName과 txtNation에 입력한 문자열을 테이블에 추가 
					
					//txtName과 txtNation의 문자열을 가져오기 
					String name = txtName.getText();
					String nation = txtNation.getText();
					
					//입력한 문자열이 없으면 메시지 대화상자를 출력하고 리턴 
					if(name.length()<1 || nation.length()<1) {
						JOptionPane.showMessageDialog(null, "이름이나 국가는 필수입력 입니다!!\n", "입력 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}	
					
					// Confirm 대화상자 출력하기
					int result = JOptionPane.showConfirmDialog(null, "정말로 추가 하시겠습니까?", "입력 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == 0) {
						// 데이터 모델에 추가할 데이터 배열을 생성
						String[] row = { name, nation };

						// 데이터 모델 가져오기
						DefaultTableModel model = (DefaultTableModel) table.getModel();

						// 데이터 추가
						model.addRow(row);
						// 원본 데이터에 추가
						// Vector <String> imsi = new Vector<String>();
						// imsi.add(name);
						// imsi.add(nation);
						// data.add(imsi);

						txtName.setText("");
						txtNation.setText("");
						JOptionPane.showMessageDialog(null, "추가 성공", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(e.getSource() == btnRemove) {
					System.out.printf("삭제 버튼\n");
				
					//테이블에서 선택한 행 번호를 가지고 행 번호에 해당하는 데이터를 삭제 
					//테이블에서 선택한 행 번호를 가져오기 
					int rowNum = table.getSelectedRow();
					//유효한 선택인지 확인해서 삭제 
					if (rowNum >= 0 && rowNum < data.size()) {
						int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION);
						
						// 데이터 모델 가져오기
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						
						//데이터 삭제 
						model.removeRow(rowNum);
						JOptionPane.showMessageDialog(null, "삭제 성공", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						return;
						
/*						// 데이터 추가
						model.removeRow(rowNum);
						// data에서도 삭제
						data.remove(rowNum);
*/						
					}
				}
				else {
					//System.out.printf("선택하고 삭제하세요\n");
					JOptionPane.showMessageDialog(null, "행을 선택하고 삭제를 누르세요", "삭제오류", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		//이벤트 처리 리스터와 컴포넌트를 연결해야 함 
		btnInsert.addActionListener(action);
		btnRemove.addActionListener(action);
				
		
		//패널을 하단에 배치
		add("South", panel);
		setVisible(true);
	}

}
