package lib;

public class TaxFunction {

    private static final int TAX_RATE = 5;
    private static final int ANNUAL_TAX_FREE_AMOUNT_SINGLE = 54000000;
    private static final int ANNUAL_TAX_FREE_AMOUNT_MARRIED = 58500000; // (54000000 + 4500000)
    private static final int ANNUAL_TAX_FREE_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_FOR_TAX_FREE = 3;

    /**
     * Menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     * 
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int annualGrossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int annualTaxFreeAmount = calculateAnnualTaxFreeAmount(isMarried, numberOfChildren);

        int taxableIncome = annualGrossIncome - deductible - annualTaxFreeAmount;
        int tax = (int) Math.round(TAX_RATE / 100.0 * taxableIncome);

        return Math.max(0, tax);
    }

    private static int calculateAnnualTaxFreeAmount(boolean isMarried, int numberOfChildren) {
        int annualTaxFreeAmount = isMarried ? ANNUAL_TAX_FREE_AMOUNT_MARRIED : ANNUAL_TAX_FREE_AMOUNT_SINGLE;
        int additionalTaxFreePerChild = Math.min(MAX_CHILDREN_FOR_TAX_FREE, numberOfChildren) * ANNUAL_TAX_FREE_PER_CHILD;
        return annualTaxFreeAmount + additionalTaxFreePerChild;
    }
}
