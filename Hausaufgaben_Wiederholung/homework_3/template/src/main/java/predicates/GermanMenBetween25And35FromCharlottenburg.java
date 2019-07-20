package predicates;

import citizens.Citizens;
import citizens.Record;

public class GermanMenBetween25And35FromCharlottenburg implements CitizenPredicate {
    @Override
    public boolean test(Record record) {
        return record.geschlecht.equals(Citizens.SEX_M)
            && record.staatsangehörigkeit.equals("D")
            && record.ortsteilName.equals("Charlottenburg")
            && record.minAlter >= 25 && record.maxAlter <= 35;
    }
}
