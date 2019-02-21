package kr.nutee.dao;

public enum FileTable {

	Article(1);

	final private int id;

	private FileTable(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
