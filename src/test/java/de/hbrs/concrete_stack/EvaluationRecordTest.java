package de.hbrs.concrete_stack;

import de.hbrs.concrete_stack.control.EvaluationRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationRecordTest {
    EvaluationRecord rec;

    @BeforeAll
    void _init(){
        rec = new EvaluationRecord(2012);
    }

    @Test
    void getYear() {
        int expact = rec.getYear();
        assertEquals(2012,expact);
    }

    @Test
    void getRatings() {
    }
}