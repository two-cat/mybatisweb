package common.controller;
//�߻�Ŭ����: execute()�߻�޼ҵ带 ����
public abstract class AbstractAction implements Action {
	
	private String viewPage; //������ �������� �̸�
	private boolean isRedirect=false;
	//������ �̵������ redirect����̸� true, forward����̸� false�� �� ����
	
	//setter, getter---
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}/////////////////////////////
