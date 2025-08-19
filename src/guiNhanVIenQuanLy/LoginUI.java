package guiNhanVIenQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("Ga Vĩnh Hành - Đăng nhập");
        setSize(930, 574);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel() {
            Image bg = new ImageIcon("resources/bg2.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Tiêu đề
        JLabel title = new JLabel("Ga Vinh Hanh", JLabel.CENTER);
        title.setFont(new Font("Brush Script MT", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 20, 300, 30);
        panel.add(title);

        // Welcome
        JLabel welcome = new JLabel("WELCOM BACK", JLabel.CENTER);
        welcome.setFont(new Font("Serif", Font.BOLD, 22));
        welcome.setForeground(Color.WHITE);
        welcome.setBounds(330, 140, 250, 30);
        panel.add(welcome);

        // Placeholder cho tài khoản
        JTextField userField = new JTextField("Tài khoản........");
        userField.setBounds(330, 180, 250, 30);
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        userField.setForeground(Color.GRAY);
        userField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (userField.getText().equals("Tài khoản........")) {
                    userField.setText("");
                    userField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (userField.getText().isEmpty()) {
                    userField.setForeground(Color.GRAY);
                    userField.setText("Tài khoản........");
                }
            }
        });
        panel.add(userField);

        // Placeholder cho mật khẩu
        JPasswordField passField = new JPasswordField("Mật khẩu........");
        passField.setEchoChar((char) 0);
        passField.setBounds(330, 220, 250, 30);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setForeground(Color.GRAY);
        passField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                String pwd = new String(passField.getPassword());
                if (pwd.equals("Mật khẩu........")) {
                    passField.setText("");
                    passField.setEchoChar('•');
                    passField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                String pwd = new String(passField.getPassword());
                if (pwd.isEmpty()) {
                    passField.setText("Mật khẩu........");
                    passField.setEchoChar((char) 0);
                    passField.setForeground(Color.GRAY);
                }
            }
        });
        panel.add(passField);

        // Quên mật khẩu căn giữa
        JLabel forgot = new JLabel("Quên mật khẩu", JLabel.CENTER);
        forgot.setForeground(Color.WHITE);
        forgot.setBounds(330, 255, 250, 20);
        forgot.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(forgot);

        // Nút đăng nhập
        JButton loginButton = new JButton("ĐĂNG NHẬP");
        loginButton.setBounds(330, 280, 250, 35);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);
        
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("admin") && password.equals("123")) {
                new MainFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Footer
        JLabel footer = new JLabel("Vĩnh Hành - Hành trình vững bước đến thành công!", JLabel.CENTER);
        footer.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        footer.setForeground(Color.WHITE);
        footer.setBounds(150, 520, 630, 15);
        panel.add(footer);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
