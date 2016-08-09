package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import passwordcracker.ReadFile;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JMenu mnFile;
    private JMenuBar menuBar;
    private JMenuItem mntmOpenDicFile, mntmOpenPwdFile, mntmOpenShadowFile;

    private File sf;
    private JFileChooser chooser;
    private String filePath = null, file;

    /**
     * Create the frame.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setVisible(true);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmOpenDicFile = new JMenuItem("Open Dictionary File");
        mntmOpenDicFile.addActionListener(this);
        mnFile.add(mntmOpenDicFile);

        mntmOpenPwdFile = new JMenuItem("Open Password File");
        mntmOpenPwdFile.addActionListener(this);
        mnFile.add(mntmOpenPwdFile);

        mntmOpenShadowFile = new JMenuItem("Open Shadow File");
        mntmOpenShadowFile.addActionListener(this);
        mnFile.add(mntmOpenShadowFile);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmOpenDicFile) {
            openFileChooser();
        } else if (e.getSource() == mntmOpenPwdFile) {
            openFileChooser();
        } else if (e.getSource() == mntmOpenShadowFile) {
            openFileChooser();
        }
    }

    public void openFileChooser() {
        chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int option = chooser.showOpenDialog(MainFrame.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            sf = chooser.getSelectedFile();
            setFilePath(sf.getPath());
            ReadFile readFile = new ReadFile(getFilePath());
        } else {

        }
    }

    public void setFilePath(String s) {
        filePath = s;
    }

    public String getFilePath() {
        return filePath;
    }
}
