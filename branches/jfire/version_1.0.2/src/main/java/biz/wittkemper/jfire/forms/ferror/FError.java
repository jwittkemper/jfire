package biz.wittkemper.jfire.forms.ferror;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.hibernate.JDBCException;

public class FError extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7500258571439273086L;
	private JButton btnOk;
	private JLabel lblFehlermeldung;
	private JLabel lblStacktrace;
	private JScrollPane scrollPane_1;
	private JTextArea errorStack;
	private JTextField lblAnzeige;
	private JTextArea errorMessage;

	public FError() {
		initFrame();
	}

	public FError(Exception exception) {
		initFrame();
		setExecption(exception);
	}

	public FError(JDBCException exception) {
		initFrame();
		StringBuilder text = new StringBuilder();

		for (StackTraceElement e : exception.getStackTrace()) {
			text.append(e.getFileName() + "-" + e.getClassName() + "-"
					+ e.getMethodName() + "-" + e.getLineNumber() + "\n");
		}
		errorStack.setText(text.toString());
		errorMessage.setText(exception.getMessage() + "\n"
				+ exception.getSQLException().getMessage());
	}

	private void setExecption(Exception exception) {
		StringBuilder text = new StringBuilder();

		for (StackTraceElement e : exception.getStackTrace()) {
			text.append(e.getFileName() + "-" + e.getClassName() + "-"
					+ e.getMethodName() + "-" + e.getLineNumber() + "\n");
		}
		errorStack.setText(text.toString());
		errorMessage.setText(exception.getMessage());
	}

	/**
	 * Create the dialog.
	 */
	private void initFrame() {
		setBounds(100, 100, 671, 660);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		lblFehlermeldung = new JLabel("Fehlermeldung");
		lblFehlermeldung.setBackground(new Color(240, 230, 140));
		lblFehlermeldung.setOpaque(true);

		lblStacktrace = new JLabel("Stacktrace");
		lblStacktrace.setBackground(new Color(240, 230, 140));
		lblStacktrace.setOpaque(true);
		scrollPane_1 = new JScrollPane();

		lblAnzeige = new JTextField();
		lblAnzeige.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAnzeige.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnzeige.setText("JFIRE Fehlermeldung");
		lblAnzeige.setEnabled(false);
		lblAnzeige.setEditable(false);
		lblAnzeige.setBackground(new Color(255, 255, 0));
		lblAnzeige.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblFehlermeldung)
								.addContainerGap(583, Short.MAX_VALUE))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(189)
								.addComponent(btnOk, GroupLayout.DEFAULT_SIZE,
										265, Short.MAX_VALUE).addGap(209))
				.addComponent(lblAnzeige, GroupLayout.DEFAULT_SIZE, 663,
						Short.MAX_VALUE)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane_1,
										GroupLayout.DEFAULT_SIZE, 643,
										Short.MAX_VALUE).addContainerGap())
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblStacktrace,
										GroupLayout.PREFERRED_SIZE, 66,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(587, Short.MAX_VALUE))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 643,
										Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(7)
						.addComponent(lblAnzeige, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblFehlermeldung)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								156, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(lblStacktrace)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE,
								339, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnOk).addGap(18)));

		errorMessage = new JTextArea();
		errorMessage.setEditable(false);
		scrollPane.setViewportView(errorMessage);

		errorStack = new JTextArea();
		errorStack.setEditable(false);
		scrollPane_1.setViewportView(errorStack);
		getContentPane().setLayout(groupLayout);

	}
}
