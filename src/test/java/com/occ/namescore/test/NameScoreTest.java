package com.occ.namescore.test;

import com.occ.namescore.model.Person;
import com.occ.namescore.service.ScoreCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

public class NameScoreTest {

    private ScoreCalculatorService scoreCalculatorService;
    private String filePath;

    @Before
    public void setup(){
        scoreCalculatorService = new ScoreCalculatorService();
        filePath = "src/main/resources/OCC Take Home Coding names short.txt";
    }

    @Test
    public void inputFilePath_OpenFileAndGetNames_thenGetPersonList() {
        List<Person> empList = scoreCalculatorService.openFileAndGetNames(filePath);
        Assert.assertNotNull(empList);
        Assert.assertEquals(9, empList.size());
    }

    @Test
    public void givenInputPersonList_whenOrderedAndCalcTotal_thenGetTotalScoreofNames() {
        List<Person> empList = scoreCalculatorService.openFileAndGetNames(filePath);
        BigInteger actualScore = scoreCalculatorService.orderAndCalcTotal(empList);
        Assert.assertNotNull(actualScore);
        Assert.assertEquals(new BigInteger("3194"), actualScore);
    }

    @Test
    public void givenPersonNameAndIndexAsInput_whenCalculateIndivdualScore_thenGetScore(){
        BigInteger score = scoreCalculatorService.findIndividualScore(new Person("MARY"), 2);
        Assert.assertNotNull(score);
        Assert.assertEquals(new BigInteger("114"), score);
    }
}
