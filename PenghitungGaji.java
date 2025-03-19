import java.util.Scanner;

class Karyawan {
    String id;
    String nama;
    String shift;
    int jamKerja;
    int hariAbsen;
    double gaji;

    public Karyawan(String id, String nama, String shift, int jamKerja, int hariAbsen) {
        this.id = id;
        this.nama = nama;
        this.shift = shift;
        this.jamKerja = jamKerja;
        this.hariAbsen = hariAbsen;
        this.gaji = hitungGaji();
    }

    private double hitungGaji() {
        double tarifPerJam = 0;
        switch (shift.toLowerCase()) {
            case "pagi":
                tarifPerJam = 50000;
                break;
            case "siang":
                tarifPerJam = 55000;
                break;
            case "malam":
                tarifPerJam = 60000;
                break;
            default:
                System.out.println("Shift tidak valid!");
                return 0;
        }

        double gajiPokok = jamKerja * tarifPerJam;
        double lembur = 0;
        double potongan = 0;

        if (jamKerja > 40) {
            int jamLembur = jamKerja - 40;
            lembur = jamLembur * tarifPerJam * 1.5; 
        } else if (jamKerja < 30) {
            potongan = gajiPokok * 0.1; 
        }

        potongan += hariAbsen * 100000; 

        return (gajiPokok + lembur - potongan);
    }

    public void tampilkanLaporan() {
        System.out.printf("\nID: %s\nNama: %s\nShift: %s\nTotal Jam: %d\nHari Absen: %d\nTotal Gaji: Rp%,.2f\n",
                id, nama, shift, jamKerja, hariAbsen, gaji);
    }
}

public class PenghitungGaji {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah karyawan: ");
        int jumlahKaryawan = input.nextInt();
        input.nextLine(); // Membersihkan buffer

        Karyawan[] daftarKaryawan = new Karyawan[jumlahKaryawan];

        for (int i = 0; i < jumlahKaryawan; i++) {
            System.out.println("\nMasukkan data karyawan ke-" + (i + 1));

            System.out.print("ID: ");
            String id = input.nextLine();

            System.out.print("Nama: ");
            String nama = input.nextLine();

            String shift;
            while (true) {
                System.out.print("Shift (pagi/siang/malam): ");
                shift = input.nextLine().toLowerCase();
                if (shift.equals("pagi") || shift.equals("siang") || shift.equals("malam")) break;
                System.out.println("Shift tidak valid! Masukkan 'pagi', 'siang', atau 'malam'.");
            }

            int jamKerja;
            while (true) {
                System.out.print("Total jam kerja dalam seminggu: ");
                jamKerja = input.nextInt();
                if (jamKerja >= 0 && jamKerja <= 70) break; // Batas wajar 0-70 jam
                System.out.println("Jam kerja tidak valid! Masukkan antara 0-70.");
            }

            int hariAbsen;
            while (true) {
                System.out.print("Jumlah hari absen: ");
                hariAbsen = input.nextInt();
                input.nextLine(); // Membersihkan buffer
                if (hariAbsen >= 0 && hariAbsen <= 7) break; // Maksimum 7 hari
                System.out.println("Jumlah absen tidak valid! Masukkan antara 0-7.");
            }

            daftarKaryawan[i] = new Karyawan(id, nama, shift, jamKerja, hariAbsen);
        }

        System.out.println("\n===== Laporan Gaji Karyawan =====");
        for (Karyawan k : daftarKaryawan) {
            k.tampilkanLaporan();
        }

        input.close();
    }
}
