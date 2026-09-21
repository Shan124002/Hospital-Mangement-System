/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.doctor;

import hospitalmanagementsystem.common.User;


/**
 *
 * @author shanshan
 */
public class Doctor extends User {

    private String specialization;

    public Doctor(String userId, String name, String password,
                  String email, String phone, String role,
                  String specialization) {

        super(userId, name, password, email, phone, role);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void editProfile(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    public void logPatientVitalSigns() {
        System.out.println("Logging patient vital signs...");
    }

    public void writeConsultationNotes() {
        System.out.println("Writing consultation notes...");
    }

    public void issuePrescription() {
        System.out.println("Issuing prescription...");
    }

    public void issueDiagnosticRequest() {
        System.out.println("Creating diagnostic request...");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Specialization: " + specialization);
    }
}