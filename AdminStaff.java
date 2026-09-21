/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import hospitalmanagementsystem.model.Insurance;
import hospitalmanagementsystem.model.HospitalAsset;
import java.io.*;
import java.util.*;
/**
 *
 * @author tbash
 */
public class AdminStaff extends User {
    private String adminPrivileges;

    public AdminStaff(String userId, String name, String password, String email, String phone) {
        super(userId, name, password, email, phone, "Admin");
        this.adminPrivileges = "Full_Access";
    }

    public AdminStaff(){
        
    }
    
    
    
    public boolean createUser(String id, String newName, String pass, String role, String newEmail, String newPhone) {
        try {
            FileWriter fwUsers = new FileWriter("Users.txt", true);
            fwUsers.write(id + "," + newName + "," + pass + "," + role + "\n");
            fwUsers.close();

            String roleFileName = role + "s.txt"; 
            FileWriter fwDetails = new FileWriter(roleFileName, true);
            
            if (role.equals("Doctor")) {

                fwDetails.write(id + "," + newName + "," + newEmail + "," + newPhone + ",Unassigned\n");
            } else {
                fwDetails.write(id + "," + newName + "," + newEmail + "," + newPhone + "\n");
            }
            fwDetails.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(String targetId, String newName, String newPassword, String role, String newEmail, String newPhone) {
        try {

            ArrayList<String> usersData = new ArrayList<>();
            File usersFile = new File("Users.txt");

            if (usersFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 4 && parts[0].equals(targetId)) {

                            usersData.add(targetId + "," + newName + "," + newPassword + "," + role);
                        } else {
                            usersData.add(line); 
                        }
                    }
                }

                try (FileWriter fw = new FileWriter(usersFile, false)) {
                    for (String record : usersData) {
                        fw.write(record + "\n");
                    }
                }
            }


            String roleFileName = role + "s.txt"; 
            ArrayList<String> detailsData = new ArrayList<>();
            File detailsFile = new File(roleFileName);

            if (detailsFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(detailsFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 4 && parts[0].equals(targetId)) {


                            if (role.equals("Doctor")) {
                                String manager = (parts.length >= 5) ? parts[4] : "Unassigned";
                                detailsData.add(targetId + "," + newName + "," + newEmail + "," + newPhone + "," + manager);
                            } else {
                                detailsData.add(targetId + "," + newName + "," + newEmail + "," + newPhone);
                            }

                        } else {
                            detailsData.add(line);
                        }
                    }
                }

                try (FileWriter fw = new FileWriter(detailsFile, false)) {
                    for (String record : detailsData) {
                        fw.write(record + "\n");
                    }
                }
            }

            return true; 

        } catch (IOException e) {
            e.printStackTrace();
            return false; 
        }
    }    


    public boolean deleteUser(String targetId, String role) {
        try {

            java.util.ArrayList<String> usersData = new java.util.ArrayList<>();
            File usersFile = new File("Users.txt");

            if (usersFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length > 0 && !parts[0].equals(targetId)) {
                            usersData.add(line);
                        }
                    }
                }

                try (FileWriter fw = new FileWriter(usersFile, false)) {
                    for (String record : usersData) {
                        fw.write(record + "\n");
                    }
                }
            }


            String roleFileName = role + "s.txt";
            java.util.ArrayList<String> detailsData = new java.util.ArrayList<>();
            File detailsFile = new File(roleFileName);

            if (detailsFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(detailsFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length > 0 && !parts[0].equals(targetId)) {
                            detailsData.add(line);
                        }
                    }
                }

                try (FileWriter fw = new FileWriter(detailsFile, false)) {
                    for (String record : detailsData) {
                        fw.write(record + "\n");
                    }
                }
            }
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean assignManager(String doctorId, String managerName) {
        try {
            java.util.ArrayList<String> doctorsData = new java.util.ArrayList<>();
            java.io.File doctorsFile = new java.io.File("Doctors.txt");

            if (doctorsFile.exists()) {
                try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(doctorsFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length >= 4 && parts[0].equals(doctorId)) {
                            String name = parts[1];
                            String email = parts[2];
                            String phone = parts[3];

                            doctorsData.add(doctorId + "," + name + "," + email + "," + phone + "," + managerName);
                        } else {
                            
                            doctorsData.add(line);
                        }
                    }
                }

                try (java.io.FileWriter fw = new java.io.FileWriter(doctorsFile, false)) {
                    for (String record : doctorsData) {
                        fw.write(record + "\n");
                    }
                }
                return true; 
            }
            return false;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkManager(String managerName){
        File file = new File("Medical Managers.txt");
        
        if(file.exists()){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                
                if(parts.length >= 2 && parts[1].equalsIgnoreCase(managerName)) {
                    return true;
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
            }   
        }
        return false;
    }
    
    public boolean checkAssets(String assetId) {
        try {
            java.io.File file = new java.io.File("Assets.txt");
            if (file.exists()) {
                java.io.FileReader fr = new java.io.FileReader(file);
                java.io.BufferedReader br = new java.io.BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(assetId)) {
                        br.close();
                        fr.close();
                        return true; 
                    }
                }
                br.close();
                fr.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean manageHospitalAssets(HospitalAsset asset) {
        try (FileWriter fw = new FileWriter("Assets.txt", true)) {
            fw.write(asset.toString() + "\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public boolean updateAsset(HospitalAsset updatedAsset) {
        java.util.ArrayList<String> fileData = new java.util.ArrayList<>();
        boolean isUpdated = false;
        
        String targetId = updatedAsset.toString().split(",")[0]; 

        try {
            java.io.File file = new java.io.File("Assets.txt");
            if (file.exists()) {
                java.io.FileReader fr = new java.io.FileReader(file);
                java.io.BufferedReader br = new java.io.BufferedReader(fr);
                String line;
                
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(targetId)) {
                        fileData.add(updatedAsset.toString());
                        isUpdated = true;
                    } else {
                        fileData.add(line);
                    }
                }
                br.close();
                fr.close();
            }
            
            if (isUpdated) {
                java.io.FileWriter fw = new java.io.FileWriter("Assets.txt", false); 
                for (String record : fileData) {
                    fw.write(record + "\n");
                }
                fw.close();
            }
            return isUpdated;
            
        } catch (Exception e) {
            System.out.println("Error updating asset: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAsset(String assetId) {
        java.util.ArrayList<String> fileData = new java.util.ArrayList<>();
        boolean isDeleted = false;

        try {
            java.io.File file = new java.io.File("Assets.txt");
            if (file.exists()) {
                java.io.FileReader fr = new java.io.FileReader(file);
                java.io.BufferedReader br = new java.io.BufferedReader(fr);
                String line;
                
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(assetId)) {
                        isDeleted = true;
                    } else {
                        fileData.add(line);
                    }
                }
                br.close();
                fr.close();
            }
            
            if (isDeleted) {
                java.io.FileWriter fw = new java.io.FileWriter("Assets.txt", false);
                for (String record : fileData) {
                    fw.write(record + "\n");
                }
                fw.close();
            }
            return isDeleted;
            
        } catch (Exception e) {
            System.out.println("Error deleting asset: " + e.getMessage());
            return false;
        }
    }
    

    public boolean updatePrice(String consultationType, String newRate) {
        java.util.ArrayList<String> fileData = new java.util.ArrayList<>();
        boolean isUpdated = false;

        try {
            java.io.File file = new java.io.File("Prices.txt");
            if (file.exists()) {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file));
                String line;
                
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].equalsIgnoreCase(consultationType)) {
                        fileData.add(consultationType + "," + newRate);
                        isUpdated = true;
                    } else {
                       fileData.add(line);
                    }
                }
                br.close();
            }
            
            if (isUpdated) {
                java.io.FileWriter fw = new java.io.FileWriter("Prices.txt", false);
                for (String record : fileData) {
                    fw.write(record + "\n");
                }
                fw.close();
            }
            return isUpdated;
            
        } catch (Exception e) {
            System.out.println("Error updating price: " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkInsurance(String id) {
        try {
            java.io.File file = new java.io.File("Insurances.txt");
            if (file.exists()) {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(id)) {
                        br.close();
                        return true;
                    }
                }
                br.close();
            }
        } catch (Exception e) {}
        return false;
    }
    
    public boolean addInsurance(Insurance insurance) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("Insurances.txt", true);
            fw.write(insurance.toString() + "\n");
            fw.close();
            return true;
        } catch (Exception e) { return false; }
    }
    
    public boolean updateInsurance(Insurance insurance) {
        java.util.ArrayList<String> data = new java.util.ArrayList<>();
        boolean updated = false;
        String targetId = insurance.toString().split(",")[0];

        try {
            java.io.File file = new java.io.File("Insurances.txt");
            if (file.exists()) {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split(",")[0].equals(targetId)) {
                        data.add(insurance.toString());
                        updated = true;
                    } else {
                        data.add(line);
                    }
                }
                br.close();
            }
            if (updated) {
                java.io.FileWriter fw = new java.io.FileWriter("Insurances.txt", false);
                for (String rec : data) fw.write(rec + "\n");
                fw.close();
            }
            return updated;
        } catch (Exception e) { return false; }
    }
    
    public boolean deleteInsurance(String id) {
        java.util.ArrayList<String> data = new java.util.ArrayList<>();
        boolean deleted = false;
        try {
            java.io.File file = new java.io.File("Insurances.txt");
            if (file.exists()) {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split(",")[0].equals(id)) {
                        deleted = true;
                    } else {
                        data.add(line);
                    }
                }
                br.close();
            }
            if (deleted) {
                java.io.FileWriter fw = new java.io.FileWriter("Insurances.txt", false);
                for (String rec : data) fw.write(rec + "\n");
                fw.close();
            }
            return deleted;
        } catch (Exception e) { return false; }
    }
    
}


