import java.util.Scanner;

public class ArenaPertarungan {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Musuh[] gelombangMonster = new Musuh[4];
        gelombangMonster[0] = new Slime();
        gelombangMonster[1] = new Naga();
        gelombangMonster[2] = new Slime();
        gelombangMonster[3] = new Zombie();

        System.out.println("========================================");
        System.out.println(" ARENA RPG: GELOMBANG MONSTER ");
        System.out.println("========================================");
        System.out.println("AWAS! Sekelompok monster menghadang Anda!");

        boolean isBermain = true;

        while (isBermain) {

            System.out.println("\n--- STATUS MONSTER ---");

            for (int i = 0; i < gelombangMonster.length; i++) {
                System.out.println((i + 1) + ". "
                        + gelombangMonster[i].namaMusuh
                        + " (HP: "
                        + gelombangMonster[i].healthPoint + ")");
            }

            System.out.println("5. Kabur dari pertarungan");
            System.out.print("\nPilih target (1-4 / 5 kabur): ");
            int pilihanTarget = input.nextInt();

            if (pilihanTarget == 5) {
                System.out.println("Anda kabur dari arena...");
                break;
            }

            if (pilihanTarget < 1 || pilihanTarget > 4) {
                System.out.println("Pilihan tidak valid!");
                continue;
            }

            System.out.print("Masukkan kekuatan serangan (10-100): ");
            int power = input.nextInt();

            int indeksMonster = pilihanTarget - 1;

            System.out.println("\n>>> HASIL SERANGAN <<<");
            gelombangMonster[indeksMonster].terimaDamage(power);

            // =============================
            // SERANGAN BALIK MONSTER
            // =============================
            for (int i = 0; i < gelombangMonster.length; i++) {

                Musuh monsterAktif = gelombangMonster[i];

                if (monsterAktif.healthPoint > 0) {

                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof Terbang) {

                        System.out.println("[PERINGATAN! SERANGAN UDARA]");
                        Terbang monsterTerbang = (Terbang) monsterAktif;

                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();

                    } else {
                        monsterAktif.serangPemain();
                    }

                } else {

                    // =============================
                    // LOOT DROP (Challenge)
                    // =============================
                    if (monsterAktif instanceof BisaLoot) {
                        BisaLoot monsterLoot = (BisaLoot) monsterAktif;
                        monsterLoot.jatuhkanItem();
                    }
                }
            }
        }

        input.close();
        System.out.println("\nGame Selesai!");
    }
}