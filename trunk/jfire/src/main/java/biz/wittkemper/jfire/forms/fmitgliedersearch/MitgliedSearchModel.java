package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;

import biz.wittkemper.jfire.data.entity.Mitglied;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

public class MitgliedSearchModel {
	private String[] columnNames;

	SelectionInList<Mitglied> mitgliedMOdel;
	ValueModel mitgliedSelectionHolder;
	SelectionInList<Mitglied> mitgliedSelectionInList;

	private final AbstractTableModel tableModel;

	public MitgliedSearchModel() {
		mitgliedMOdel = new SelectionInList<Mitglied>();
		mitgliedSelectionHolder = new ValueHolder();
		mitgliedSelectionInList = new SelectionInList<Mitglied>(
				(ListModel) mitgliedMOdel, mitgliedSelectionHolder);

		tableModel = new ExampleTableModel();
	}

	public void setData(List<Mitglied> liste) {
		mitgliedMOdel.clearSelection();
		mitgliedMOdel.setList(liste);
		tableModel.fireTableDataChanged();

	}

	public AbstractTableModel getTableModel() {
		return tableModel;
	}

	public class ExampleTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public ExampleTableModel() {
			setColumnNames();
		}

		@Override
		public int getRowCount() {
			return mitgliedSelectionInList.getSize();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public void setColumnNames() {
			columnNames = new String[] { "id", "Vorname", "Name" };
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			Mitglied md = mitgliedSelectionInList.getElementAt(rowIndex);
			switch (columnIndex) {
			case 0:
				return md.getId();
			case 1:
				return md.getVorname();
			case 2:
				return md.getName();
			}
			return "";
		}
	}

	public Mitglied getSelectedMitglied(int row) {
		return mitgliedSelectionInList.getElementAt(row);
	}

}
