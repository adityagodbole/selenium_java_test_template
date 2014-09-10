package common.web;


/**
 * Created by aditya on 06/09/14.
 */

class navStatus {
    private Boolean status = false;
	private Class<?> to = null;

	public void navigate(Class<?> pageClass) {
        this.to = pageClass;
        if (pageClass == null) {
            status = false;
        }
        else status = true;
    }

    public Boolean navigated() {
        return status;
    }

	public Boolean navigated(Class<?> to) {
        return  (to == this.to);
    }
}
