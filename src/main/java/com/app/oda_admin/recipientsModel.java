package com.app.oda_admin;

public class recipientsModel {

    private String recipient_name;
    private String requestedOrgan;
    private String recipient_bloodGroup;

    private recipientsModel() {
    }

    recipientsModel(String recipient_name, String requestedOrgan, String recipient_bloodGroup) {
        this.recipient_name = recipient_name;
        this.requestedOrgan = requestedOrgan;
        this.recipient_bloodGroup = recipient_bloodGroup;
    }

    public String getRecipient_bloodGroup() {
        return recipient_bloodGroup;
    }

    public void setRecipient_bloodGroup(String recipient_bloodGroup) {
        this.recipient_bloodGroup = recipient_bloodGroup;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getRequestedOrgan() {
        return requestedOrgan;
    }

    public void setRequestedOrgan(String requestedOrgan) {
        this.requestedOrgan = requestedOrgan;
    }
}
