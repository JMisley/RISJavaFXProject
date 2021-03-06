package com.risjavafx.pages.orders;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
@SuppressWarnings("unused")
public class OrdersData {
    SimpleIntegerProperty orderIdData;
    SimpleStringProperty patientData;
    SimpleStringProperty referralMdData;
    SimpleStringProperty modalityData;
    SimpleStringProperty notesData;
    SimpleStringProperty statusData;
    SimpleStringProperty reportData;

    public OrdersData(int orderId, String patient, String referralMd, String modality, String notes, String status) {
        this.orderIdData = new SimpleIntegerProperty(orderId);
        this.patientData = new SimpleStringProperty(patient);
        this.referralMdData = new SimpleStringProperty(referralMd);
        this.modalityData = new SimpleStringProperty(modality);
        this.notesData = new SimpleStringProperty(notes);
        this.statusData = new SimpleStringProperty(status);
    }

    public int getOrderIdData() {
        return orderIdData.get();
    }

    public void setOrderIdData(int orderId) {
        this.orderIdData.set(orderId);
    }

    public String getPatientData() {
        return patientData.get();
    }

    public void setPatientData(String patientData) {
        this.patientData.set(patientData);
    }

    public String getReferralMdData() {
        return referralMdData.get();
    }

    public void setReferralMdData(String referralMdData) {
        this.referralMdData.set(referralMdData);
    }

    public String getModalityData() {
        return modalityData.get();
    }

    public void setModalityData(String modalityData) {
        this.modalityData.set(modalityData);
    }

    public String getNotesData() {
        return notesData.get();
    }

    public void setNotesData(String notesData) {
        this.notesData.set(notesData);
    }

    public String getStatusData() {
        return statusData.get();
    }

    public void setStatusData(String statusData) {
        this.statusData.set(statusData);
    }
}
