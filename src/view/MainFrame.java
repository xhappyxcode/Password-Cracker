package view;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

import passwordcracker.*;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

public class MainFrame extends JFrame implements ActionListener {

	private JButton btnCrackPassword, btnSave;
	private JLabel lblDictionary, lblPassword, lblShadowFile;
	private JPanel contentPane, pnlCols, pnlFiles, pnlOutput;
	private TextArea txtAreaDict, txtAreaPwd, txtAreaShade, txtAreaOutput;
	
	private File sf;
	private Controller mc;
	private String filePath, textData;
	
	/**
	 * Create the frame.
	 */
	public MainFrame(Controller mc) {
		this.mc = mc;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1250, 725);
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlFiles = new JPanel();
		pnlFiles.setBorder(new TitledBorder(null, "Files", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiles.setBounds(4, 1, 1226, 425);
		pnlFiles.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnlFiles);
		pnlFiles.setLayout(null);
		
		pnlCols = new JPanel();
		pnlCols.setBounds(6, 16, 1214, 400);
		pnlCols.setBackground(Color.LIGHT_GRAY);
		pnlFiles.add(pnlCols);
		pnlCols.setLayout(null);
		
		btnCrackPassword = new JButton("Crack");
		btnCrackPassword.setBounds(1095, 30, 109, 23);
		btnCrackPassword.addActionListener(this);
		pnlCols.add(btnCrackPassword);
		
		lblDictionary = new JLabel("Dictionary:");
		lblDictionary.setBounds(10, 15, 65, 14);
		pnlCols.add(lblDictionary);
		
		lblPassword = new JLabel("Password File:");
		lblPassword.setBounds(216, 15, 109, 14);
		pnlCols.add(lblPassword);
		
		lblShadowFile = new JLabel("Shadow File:");
		lblShadowFile.setBounds(582, 15, 109, 14);
		pnlCols.add(lblShadowFile);
		
		txtAreaDict = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtAreaDict.setBackground(Color.WHITE);
		txtAreaDict.setEditable(false);
		txtAreaDict.setBounds(10, 30, 200, 359);
		pnlCols.add(txtAreaDict);
		
		txtAreaPwd = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtAreaPwd.setBackground(Color.WHITE);
		txtAreaPwd.setEditable(false);
		txtAreaPwd.setBounds(216, 30, 360, 359);
		pnlCols.add(txtAreaPwd);
		
		txtAreaShade = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtAreaShade.setBackground(Color.WHITE);
		txtAreaShade.setEditable(false);
		txtAreaShade.setBounds(582, 30, 502, 359);
		pnlCols.add(txtAreaShade);
		
		pnlOutput = new JPanel();
		pnlOutput.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlOutput.setBounds(4, 437, 1226, 239);
		pnlOutput.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnlOutput);
		pnlOutput.setLayout(null);
		
		txtAreaOutput = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtAreaOutput.setBounds(10, 20, 1080, 209);
		txtAreaOutput.setEditable(false);
		pnlOutput.add(txtAreaOutput);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(1107, 20, 109, 23);
		btnSave.addActionListener(this);
		pnlOutput.add(btnSave);
		
		setTxtAreaDict();
		setTxtAreaPwd();
		setTxtAreaShade();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCrackPassword) {
			mc.readPasswdData();
			mc.readShadowData();
			
			mc.crackPassword();
			txtAreaOutput.setText(mc.getResult());
		} else if(e.getSource() == btnSave) {
			mc.createFile(txtAreaOutput.getText());
			JOptionPane.showMessageDialog(null, "Results are saved.");
		}
    }
	
	public void setTxtAreaDict() {
		StringBuilder sd = new StringBuilder();
		ArrayList<String> dd = mc.getDictionaryData();
		for(int i = 0; i < dd.size(); i++) {
			sd.append(dd.get(i));
			sd.append('\n');
		}
		txtAreaDict.setText(sd.toString());
	}
	
	public void setTxtAreaPwd() {
		StringBuilder sp = new StringBuilder();
		ArrayList<String> pd = mc.getPasswdData();
		for(int i = 0; i < pd.size(); i++) {
			sp.append(pd.get(i));
			sp.append('\n');
		}
		txtAreaPwd.setText(sp.toString());
	}
	
	public void setTxtAreaShade() {
		StringBuilder ss = new StringBuilder();
		ArrayList<String> sd = mc.getShadowData();
		for(int i = 0; i < sd.size(); i++) {
			ss.append(sd.get(i));
			ss.append('\n');
		}
		txtAreaShade.setText(ss.toString());
	}
}
