<%@ page import="com.project.admin.*"%>
<%
            session.setMaxInactiveInterval(2500000);
            QrUserSession appSessUser = new QrUserSession();
            User user = new User();
            try {
                if (session.getValue("ADMIN_LOGIN") != null) {
                    appSessUser = (QrUserSession) session.getValue("ADMIN_LOGIN");
                    user = appSessUser.getUser();
                    try {
                        user = DbUser.fetch(user.getOID());
                    } catch (Exception ex) {
                    }
                } else {
                    appSessUser = null;
                    response.sendRedirect(approot + "/indexsp.jsp");
                }

            } catch (Exception exc) {                
                appSessUser = null;
                response.sendRedirect(approot + "/indexsp.jsp");
            }

            if (appSessUser == null) {
                appSessUser = new QrUserSession();
            }

            boolean spPinjamanKopBaru = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU);
            boolean spPinjamanKopAngsuran = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ANGSURAN);
            boolean spPinjamanKopArsip = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP);
            boolean spPinjamanAnggKopBaru = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_KOP_BARU);
            boolean spPinjamanAnggKopAngsuran = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_KOP_ANGSURAN);
            boolean spPinjamanAnggKopArsip = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_KOP_ARSIP);            
            
            boolean spPinjamanAnggBankBaru = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_BARU);
            boolean spPinjamanAnggBankAngsuran = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN);
            boolean spPinjamanAnggBankArsip = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ARSIP);
            
            boolean spApprovePinjamanKop = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_APPROVE_PINJAMAN_KOP_BARU);            
            boolean spBayarPinjamanKop = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_BAYAR_PINJAMAN_KOP);
            boolean spPelunasanPinjamanKop = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PELUNASAN_PINJAMAN_KOP);
            
            boolean spApprovePinjamanAngg = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_APPROVE_PINJAMAN_ANGG_BARU);            
            boolean spBayarPinjamanAngg = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_BAYAR_PINJAMAN_ANGG);
            boolean spPelunasanPinjamanAngg = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PELUNASAN_PINJAMAN_ANGG);
            
            boolean spApprovePinjamanAnggBank = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_APPROVE_PINJAMAN_ANGG_BANK);            
            boolean spBayarPinjamanAnggBank = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_BAYAR_PINJAMAN_ANGG_BANK);
            boolean spPelunasanPinjamanBank = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PELUNASAN_PINJAMAN_ANGG_BANK);

            boolean spDaftarAkunPiutang = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_DAFTAR_AKUN_PIUTANG);
            boolean spDaftarAkunHutang = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_DAFTAR_AKUN_HUTANG);

            boolean spSimpananPeriodeRekap = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_SIMPANAN, AppMenu.M2_SIPADU_SIMPANAN_PERIODE_REKAP);
            boolean spSimpananPeriodePotGaji = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_SIMPANAN, AppMenu.M2_SIPADU_SIMPANAN_REKAP_POTONGAN_GAJI);
            boolean spSimpananBuku = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_SIMPANAN, AppMenu.M2_SIPADU_SIMPANAN_BUKU);
            boolean spSimpananSaldo = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_SIMPANAN, AppMenu.M2_SIPADU_SIMPANAN_SALDO);

            boolean spLapSaldoHutang = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_LAPORAN, AppMenu.M2_SIPADU_LAPORAN_SALDO_HUTANG);
            boolean spLapSaldoPiutang = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_LAPORAN, AppMenu.M2_SIPADU_LAPORAN_SALDO_PIUTANG);
            boolean spLapPembayaran = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_LAPORAN, AppMenu.M2_SIPADU_LAPORAN_PEMBAYARAN);

            boolean spMasterAnggota = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA);
            
            boolean spDaftarPengguna = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_ADMINISTRATOR, AppMenu.M2_SIPADU_DAFTAR_PENGGUNA);
            boolean spPengelompokanPengguna =appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_ADMINISTRATOR, AppMenu.M2_SIPADU_PENGELOMPOKAN_PENGGUNA);

%>
