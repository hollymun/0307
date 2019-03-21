import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ListWindow extends JFrame{
	//리스트 2개와 버튼 1개 

	private JList leftList, rightList;
	private JButton btn; 
	Label lbl;
	
	//List에 출력할 배열 만들기 
	String [] players = {"하루", "박시", "후추", "호두", "감자", "똘이"};
	
	public ListWindow() {
		setTitle("목록 옮기기");
		setSize(300, 300);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//레이아웃 변경 
		setLayout(new FlowLayout());
		
		JLabel lbl = new JLabel("츄르 먹은 고양이 제외하기");
		add(lbl);
		
		leftList = new JList(players);
		//다중 선택 모드로 설정 
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(leftList);
		
		btn = new JButton(">>");
		add(btn);
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//leftList에서 선택한 항목의 인덱스 가져오기 
				int [] idx = leftList.getSelectedIndices();

				//배열을 가지고 List 생성 
				List<String> list = new ArrayList<String>();
				for(String imsi : players) {
					list.add(imsi);
				}
				//list에서 idx에 있는 데이터들을 삭제 
				//순서대로 저장된 인덱스의 데이터를 지울 때는 주의 
				//앞에서부터 지우면 뒤쪽 인덱스가 변경되므로 뒤에서부터 삭제해야 함
				
				for(int i=0; i<idx.length; i=i+1) {
					//배열에서 인덱스 *번째에 해당하는 데이터를 가져와서 삭제 
					list.remove(idx[idx.length-i-1]);
				}
				System.out.printf("%s\n", list);
			}
			
		};
		btn.addActionListener(action);
		
		
		rightList = new JList();
		add(rightList);
		
		
		
		setVisible(true);
	}

}
