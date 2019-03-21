import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonWindow extends JFrame {
	
	private JLabel lblGender, lblLanguage;
	private JButton btnConfirm;
	private JRadioButton radioMan, radioWoman;
	private JCheckBox cbCPlus, cbJava, cbPython, cbJavascript;
	
	public ButtonWindow() {
		//현재 자신의 윈도우 크기를 가져오기 
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenSize = t.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		setTitle("버튼 테스트");
		setSize(800, 100);
		setLocation((width-800)/2, (height-300)/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();

		lblGender = new JLabel("성별");
		radioWoman = new JRadioButton("여자");
		radioMan = new JRadioButton("남자");
		radioWoman.setSelected(true);
		
		//라디오 버튼은 그룹으로 생성 
		ButtonGroup group = new ButtonGroup();
		group.add(radioWoman);
		group.add(radioMan);
		
		panel.add(lblGender);
		panel.add(radioWoman);
		panel.add(radioMan);
		
		lblLanguage = new JLabel("프로그래밍 언어");
		cbCPlus = new JCheckBox("C++");
		cbJava = new JCheckBox("자바");
		cbPython = new JCheckBox("파이썬");
		cbJavascript = new JCheckBox("자바스크립트");
		
		panel.add(lblLanguage);
		panel.add(cbCPlus);
		panel.add(cbJava);
		panel.add(cbPython);
		panel.add(cbJavascript);
		
		btnConfirm = new JButton("확인");
		btnConfirm.setToolTipText("누르면 다음 화면으로 넘어갑니다");
		panel.add(btnConfirm);
		
		//이벤트 처리 
		//버튼을 클릭하거나 텍스트 필드에서 enter를 눌렀을 때 
		//이벤트 처리를 위한 인터페이스가 구현된 객체 생성 
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.printf("버튼을 누름\n");
				//성별 선택과 프로그래밍 언어를 선택한 것을 출력 
				
				//파일 열기 대화상자 생성
				JFileChooser fc = new JFileChooser("/Users/mac/Documents");
				

				FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg|.png", "jpg", "png");
				fc.setFileFilter(filter);

				//showOpenDialog 대신에 showSaveDialog를 호출하면 저장 대화상자 
				int result = fc.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					//선택한 파일경로를 출력 
					System.out.printf("%s를 선택\n", fc.getSelectedFile().getAbsolutePath());
				}
				
				
				//fc.showOpenDialog(null);
				
			}
			
		};
		btnConfirm.addActionListener(action);
		
		
		add(panel);
		
		
		
		setVisible(true);
		
	}
	

}
