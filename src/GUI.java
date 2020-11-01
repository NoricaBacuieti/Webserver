import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {


    public JFrame frame = new JFrame();
    public static String status = "Stopped";
    public JButton start_stop_server_button = new JButton("Start server");
    public JCheckBox checkbox = new JCheckBox("Switch to maintenance mode");
    public JTextField server_listening_on_port_value = new JTextField("8080");
    public JTextField web_root_directory_value = new JTextField("C:\\Users\\noric\\Desktop\\Pages\\index");
    public JTextField maintenance_directory_value = new JTextField("C:\\Users\\noric\\Desktop\\Pages\\maintenance");



    public GUI() {

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        TitledBorder title1 = BorderFactory.createTitledBorder(loweredetched, "Web Server information");
        panel1.setBorder(title1);

        TitledBorder title2 = BorderFactory.createTitledBorder(loweredetched, "Web Server control");
        panel2.setBorder(title2);

        TitledBorder title3 = BorderFactory.createTitledBorder(loweredetched, "Web Server configuration");
        panel3.setBorder(title3);

        panel1.setLayout(new GridLayout(3, 2));
        panel2.setLayout(new GridLayout(2, 0));
        panel3.setLayout(new GridLayout(3, 3));

        JTextField server_status = new JTextField("Server status:");
        server_status.setEditable(false);
        server_status.setBorder(null);

        JTextField server_status_value = new JTextField("not running");
        server_status_value.setEditable(false);
        server_status_value.setBorder(null);

        JTextField server_address = new JTextField("Server address:");
        server_address.setEditable(false);
        server_address.setBorder(null);

        JTextField server_address_value = new JTextField("not running");
        server_address_value.setEditable(false);
        server_address_value.setBorder(null);

        JTextField server_listening_port = new JTextField("Server listening port:");
        server_listening_port.setEditable(false);
        server_listening_port.setBorder(null);

        JTextField server_listening_port_value = new JTextField("not running");
        server_listening_port_value.setEditable(false);
        server_listening_port_value.setBorder(null);

        panel1.add(server_status);
        panel1.add(server_status_value);
        panel1.add(server_address);
        panel1.add(server_address_value);
        panel1.add(server_listening_port);
        panel1.add(server_listening_port_value);



        //JButton start_stop_server_button = new JButton("Start server");
        start_stop_server_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                checkbox.setSelected(false);
                if(start_stop_server_button.getText().equals("Start server"))
                {
                    start_stop_server_button.setText("Stop server");
                    update_server_status_and_field_editability("Running",false, false, true, true);

                }
                else
                {
                    start_stop_server_button.setText("Start server");
                    update_server_status_and_field_editability("Stopped",true, true, true, false);

                }

            }
        });
        start_stop_server_button.setEnabled(true);

        //JCheckBox checkbox = new JCheckBox("Switch to maintenance mode");
        checkbox.setSelected(false);
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(checkbox.isSelected())
                    update_server_status_and_field_editability("Maintenance",false, true, false, true);
                else
                    update_server_status_and_field_editability("Running",false, false, true, true);


            }
        });
        checkbox.setEnabled(false);
        panel2.add(start_stop_server_button, BorderLayout.CENTER);
        panel2.add(checkbox, BorderLayout.CENTER);




        JTextField server_listening_on_port_x = new JTextField("Server listening on port:");
        server_listening_on_port_x.setEditable(false);
        server_listening_on_port_x.setBorder(null);

        //JTextField server_listening_on_port_value = new JTextField("8080");
        server_listening_on_port_value.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(server_listening_on_port_value.getText());
            }
        });
        server_listening_on_port_value.setEnabled(true);
        server_listening_on_port_value.setBorder(null);

        JTextField web_root_directory = new JTextField("Web root directory:");
        web_root_directory.setEditable(false);
        web_root_directory.setBorder(null);

        //JTextField web_root_directory_value = new JTextField("C:\\Users\\noric\\Desktop\\Pages\\index");
        web_root_directory_value.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(web_root_directory_value.getText());
            }
        });
        web_root_directory_value.setEnabled(true);
        web_root_directory_value.setBorder(null);

        JTextField maintenance_directory = new JTextField("Maintenance directory");
        maintenance_directory.setEditable(false);
        maintenance_directory.setBorder(null);

        //JTextField maintenance_directory_value = new JTextField("C:\\Users\\noric\\Desktop\\Pages\\maintenance");
        maintenance_directory_value.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(maintenance_directory_value.getText());
            }
        });
        maintenance_directory_value.setEnabled(true);
        maintenance_directory_value.setBorder(null);

        JTextField x_sign = new JTextField("X");
        x_sign.setEditable(false);
        x_sign.setForeground(Color.RED);
        x_sign.setBorder(null);


        JTextField o_sign = new JTextField("O");
        o_sign.setEditable(false);
        o_sign.setForeground(Color.GREEN);
        o_sign.setBorder(null);

        JTextField blanc_field = new JTextField("");
        blanc_field.setEditable(false);
        blanc_field.setBorder(null);

        panel3.add(server_listening_on_port_x);
        panel3.add(server_listening_on_port_value);
        panel3.add(blanc_field);
        panel3.add(web_root_directory);
        panel3.add(web_root_directory_value);
        panel3.add(o_sign);
        panel3.add(maintenance_directory);
        panel3.add(maintenance_directory_value);

        x_sign.setText("O");
        x_sign.setForeground(Color.GREEN);
        panel3.add(x_sign);


        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Web Server:" + status);
        frame.pack();
        frame.setVisible(true);


    }

    public void update_server_status_and_field_editability(String server_status, Boolean port_editability, Boolean rootDir_editability, Boolean maintenanceDir_editability, Boolean maintenanceCheckbox_editability)
    {
        status = server_status;
        frame.setTitle("Web Server:" + status);
        server_listening_on_port_value.setEnabled(port_editability);
        web_root_directory_value.setEnabled(rootDir_editability);
        maintenance_directory_value.setEnabled(maintenanceDir_editability);
        checkbox.setEnabled(maintenanceCheckbox_editability);
    }


    public static void main(String[] args) {

        new GUI();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}