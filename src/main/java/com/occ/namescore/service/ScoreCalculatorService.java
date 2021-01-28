package com.occ.namescore.service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.occ.namescore.model.Person;
import com.occ.namescore.exception.NameScoreException;
import com.occ.namescore.util.NameScoreConstants;
import java.lang.String;

public class ScoreCalculatorService {

    private static Logger logger = LoggerFactory.getLogger(ScoreCalculatorService.class);

    /**
     * Method to open file and get the names list
     *
     * @param filePath
     * @return
     * @throws NameScoreException
     */
    public List<Person> openFileAndGetNames(String filePath) throws NameScoreException{
        logger.info("Opening the input file -> File name: {}", filePath);
        List<String> dataList = new ArrayList<>();
        List<Person> nameList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(i -> dataList.addAll(Arrays.asList(i.split(NameScoreConstants.COMMA))));
            dataList.forEach(name -> nameList.add(
                    new Person(StringUtils.replace(name, NameScoreConstants.DOUBLE_QUOTE, StringUtils.EMPTY))));
        } catch (IOException exception) {
            logger.error("Error in reading file: {}", exception.getMessage());
            throw new NameScoreException("File Processing error", exception);
        }
        return nameList;
    }

    /**
     * Method to sort the names and find the total score
     *
     * @param nameList
     * @return
     * @throws NameScoreException
     */
    public BigInteger orderAndCalcTotal(List<Person> nameList) throws NameScoreException {
        nameList.sort(Comparator.comparing(Person::getFirstName));
        BigInteger totalScore = new BigInteger(NameScoreConstants.ZERO);
        Integer i = NameScoreConstants.START_INDEX;
        for (Person name : nameList) {
            totalScore = totalScore.add(findIndividualScore(name, i++));
        }
        return totalScore;
    }

    /**
     * Method to calculate score for each name
     *
     * @param name
     * @param order
     * @return
     * @throws NameScoreException
     */
    public BigInteger findIndividualScore(Person name, Integer order) throws NameScoreException {
        BigInteger score = new BigInteger(NameScoreConstants.ZERO);
        for (char nameChar : name.getFirstName().toCharArray()) {
            score = score.add(BigInteger.valueOf(nameChar - NameScoreConstants.CAPITAL_LETTER_ASCII));
        }
        return score.multiply(BigInteger.valueOf(order));
    }
}
