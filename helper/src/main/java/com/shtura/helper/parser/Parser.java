package com.shtura.helper.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
//import com.google.common.collect.LinkedListMultimap;
//import com.google.common.collect.Multimap;
import com.shtura.helper.entity.helperdb.ConnectionIPAdress;
import com.shtura.helper.entity.helperdb.ConnectionNameOrganization;
import com.shtura.helper.repositories.helperdb.ConnectionIPAdressRepository;
import com.shtura.helper.repositories.helperdb.ConnectionNameOrganizationRepository;


@Component
public class Parser {
    
    private static ConnectionNameOrganizationRepository connectionNameOrganizationRepository;
    
    @Autowired
    private ConnectionNameOrganizationRepository autowiredConnectionNameOrganizationRepository;
    
    private static ConnectionIPAdressRepository connectionIPAdressRepository;
    
    @Autowired
    private ConnectionIPAdressRepository autowiredConnectionIPAdressRepository;

    private static String inputFileName = "src/main/resources/connectionList/connectionList.xls";
    private static String OUTPUTFILEDIR = ".src/main/resources/connectionList/";
    
    public static void xlsParser(String inputFileName) throws Exception {
        
        HSSFWorkbook wb = readWorkbook(inputFileName);
        
        HSSFSheet sheet= wb.getSheetAt(0);
        
        HSSFRow row;
        HSSFCell cellOrganization;
        HSSFCell cellName;
        HSSFCell cellIP;
        
        Multimap<String, DataHolderConnect> connections = LinkedListMultimap.create();

        HashSet<String> mySetOrganizations = new LinkedHashSet<>();

        Integer rowsNumber = 0;
        rowsNumber = sheet.getLastRowNum();

        for( int i = 0; i <= rowsNumber; i++) {
            
            row = sheet.getRow(i);
            DataHolderConnect dataHolder = new DataHolderConnect();
                
                cellOrganization = row.getCell(0);
                cellName = row.getCell(1);
                cellIP = row.getCell(2);
                
                dataHolder.setName(cellName.toString());
                dataHolder.setIp(cellIP.toString());
                
                mySetOrganizations.add(cellOrganization.toString());
                
                connections.put(cellOrganization.toString(), dataHolder); // переопределить метод toHash для корректного сравнения при добавлении в map
            
        }
        
        freeTablesBeforeInsert();
        
        Iterator<String> iterator = mySetOrganizations.iterator();
        while (iterator.hasNext()) {
            
            String currentOrganization = iterator.next();
            
            if( currentOrganization.equals("organization") && iterator.hasNext()) {
                currentOrganization = iterator.next();
            }

            connectionNameOrganizationRepository.save(new ConnectionNameOrganization(currentOrganization));

            List<DataHolderConnect> list = new ArrayList<DataHolderConnect>(connections.get(currentOrganization));
            
            for(int i = 0; i < list.size(); i++) {
                
                List<ConnectionNameOrganization> listConnectionNameOrganization = connectionNameOrganizationRepository.findByName(currentOrganization);
                
                if(!listConnectionNameOrganization.isEmpty()) {
                    ConnectionIPAdress connectionIPAdress = new ConnectionIPAdress();
                    
                    connectionIPAdress.setConnectionNameOrganization(listConnectionNameOrganization.get(0));
                    connectionIPAdress.setIp(list.get(i).getIp());
                    connectionIPAdress.setName(list.get(i).getName());
                    
                    connectionIPAdressRepository.save(connectionIPAdress);
                }
            }
        }
    }
    
    //free tables before insert from file
    private static void freeTablesBeforeInsert() {
        
        List<ConnectionIPAdress> listConnectionIPAdressForDelete = (List<ConnectionIPAdress>) connectionIPAdressRepository.findAll();
        for(int i = 0; i < listConnectionIPAdressForDelete.size(); i++) {
            connectionIPAdressRepository.delete(listConnectionIPAdressForDelete.get(i));
        }
        
        List<ConnectionNameOrganization> listConnectionNameOrganizationForDelete = (List<ConnectionNameOrganization>) connectionNameOrganizationRepository.findAll();
        for(int i = 0; i < listConnectionNameOrganizationForDelete.size(); i++) {
            connectionNameOrganizationRepository.delete(listConnectionNameOrganizationForDelete.get(i));
        }
    }

    public static HSSFWorkbook readWorkbook(String filename) throws Exception {

        File file = new File(filename);
        
        FileInputStream fileInputStream = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        return wb;
    }
    
    @PostConstruct
    private void init() {
        connectionNameOrganizationRepository = this.autowiredConnectionNameOrganizationRepository;
        
        connectionIPAdressRepository = this.autowiredConnectionIPAdressRepository;
    }

    public static void saveToFileFromDB() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();

        System.out.println(new File("").getAbsolutePath());

        String filePath = new File("").getAbsolutePath() + "/src/main/resources/connections/files/" + "connectionList " + dateFormat.format(date) + ".xls";

        System.out.println(filePath);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet");

        HSSFRow rowhead = sheet.createRow((short)0);
        rowhead.createCell(0).setCellValue("organization");
        rowhead.createCell(1).setCellValue("name");
        rowhead.createCell(2).setCellValue("ip");

        List<ConnectionIPAdress> listConnectionIPAdress = (List<ConnectionIPAdress>) connectionIPAdressRepository.findAll();

        for(int i = 0; i < listConnectionIPAdress.size(); i++) {
            
            HSSFRow row = sheet.createRow((short)i+1);
            row.createCell(0).setCellValue(listConnectionIPAdress.get(i).getConnectionNameOrganization().getName());
            row.createCell(1).setCellValue(listConnectionIPAdress.get(i).getName());
            row.createCell(2).setCellValue(listConnectionIPAdress.get(i).getIp());
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        
        System.out.println("********** Your excel file has been generated! **********");
    }
}
