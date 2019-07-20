package citizenlist;

import citizens.Citizens;
import citizens.Record;

public class GermanMan_25_35 implements CitizenPredicate {
    @Override
    public boolean test(Record record) {
        return record.geschlecht.equals(Citizens.SEX_M)
            && record.staatsangehörigkeit.equals("D")
            && record.minAlter >= 25
            && record.maxAlter <= 35
            && record.ortsteilName.equals("Blankenfelde");
    }
}
