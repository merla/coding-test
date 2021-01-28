package com.occ.namescore;


import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.occ.namescore.exception.NameScoreException;
import com.occ.namescore.model.Person;
import com.occ.namescore.service.ScoreCalculatorService;
import com.occ.namescore.util.NameScoreConstants;

/**
 * This is the Main class for the project to  calculate the total score of all the persons from input file
 *
 * @author Prathyusha Rani Merla
 *
 */
public class NameScoreApplication {

    private static Logger logger = LoggerFactory.getLogger(NameScoreApplication.class);

    public static void main(String[] args) {
        try {
            BasicConfigurator.configure();
            String filePath = args[0];
            ScoreCalculatorService computeScoreService = new ScoreCalculatorService();
            List<Person> nameList = computeScoreService.openFileAndGetNames(filePath);
            logger.info("Total compute score for the list of first names from the input file {} is", filePath, computeScoreService.orderAndCalcTotal(nameList));

        } catch (NameScoreException ex) {
            logger.error("Exception occured: {}", ex.fillInStackTrace());
            System.exit(NameScoreConstants.EXIT_ERROR_CODE);
        }
    }

}