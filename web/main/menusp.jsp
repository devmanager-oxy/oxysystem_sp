
<%
            menuIdx = JSPRequestValue.requestInt(request, "menu_idx");
%>
<script language="JavaScript">
    
    function cmdChangeMenu(idx){
        var x = idx;
        
        switch(parseInt(idx)){                    
            
            case 1 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="none";
            document.all.pinjaman2.style.display="";
            document.all.pinjaman.style.display="";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="";
            document.all.simpanan2.style.display="none";
            document.all.simpanan.style.display="none";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="";
            document.all.laporan2.style.display="none";
            document.all.laporan.style.display="none";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="";
            document.all.masterdata2.style.display="none";
            document.all.masterdata.style.display="none";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="";
            document.all.admin2.style.display="none";
            document.all.admin.style.display="none";
            <%}%>
            
            break;
            
            case 2 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="";
            document.all.pinjaman2.style.display="none";
            document.all.pinjaman.style.display="none";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="none";
            document.all.simpanan2.style.display="";
            document.all.simpanan.style.display="";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="";
            document.all.laporan2.style.display="none";
            document.all.laporan.style.display="none";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="";
            document.all.masterdata2.style.display="none";
            document.all.masterdata.style.display="none";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="";
            document.all.admin2.style.display="none";
            document.all.admin.style.display="none";
            <%}%>
            
            break;
            
            case 3 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="";
            document.all.pinjaman2.style.display="none";
            document.all.pinjaman.style.display="none";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="";
            document.all.simpanan2.style.display="none";
            document.all.simpanan.style.display="none";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="none";
            document.all.laporan2.style.display="";
            document.all.laporan.style.display="";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="";
            document.all.masterdata2.style.display="none";
            document.all.masterdata.style.display="none";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="";
            document.all.admin2.style.display="none";
            document.all.admin.style.display="none";
            <%}%>
            
            break;
            
            case 4 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="";
            document.all.pinjaman2.style.display="none";
            document.all.pinjaman.style.display="none";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="";
            document.all.simpanan2.style.display="none";
            document.all.simpanan.style.display="none";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="";
            document.all.laporan2.style.display="none";
            document.all.laporan.style.display="none";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="none";
            document.all.masterdata2.style.display="";
            document.all.masterdata.style.display="";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="";
            document.all.admin2.style.display="none";
            document.all.admin.style.display="none";
            <%}%>
            
            break;
            
            case 5 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="";
            document.all.pinjaman2.style.display="none";
            document.all.pinjaman.style.display="none";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="";
            document.all.simpanan2.style.display="none";
            document.all.simpanan.style.display="none";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="";
            document.all.laporan2.style.display="none";
            document.all.laporan.style.display="none";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="";
            document.all.masterdata2.style.display="none";
            document.all.masterdata.style.display="none";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="none";
            document.all.admin2.style.display="";
            document.all.admin.style.display="";
            <%}%>
            
            break;
            
            
            case 0 :
            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
            document.all.pinjaman1.style.display="";
            document.all.pinjaman2.style.display="none";
            document.all.pinjaman.style.display="none";
            <%}%>
            
            <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
            document.all.simpanan1.style.display="";
            document.all.simpanan2.style.display="none";
            document.all.simpanan.style.display="none";
            <%}%>
            
            <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
            document.all.laporan1.style.display="";
            document.all.laporan2.style.display="none";
            document.all.laporan.style.display="none";
            <%}%>
            
            <%if (spMasterAnggota) {%>
            document.all.masterdata1.style.display="";
            document.all.masterdata2.style.display="none";
            document.all.masterdata.style.display="none";
            <%}%>
            
            <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
            document.all.admin1.style.display="";
            document.all.admin2.style.display="none";
            document.all.admin.style.display="none";
            <%}%>
            
            break;	
        }
    }
    
    </script>
<table width="180" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td style="padding-left:5px" style="border-right:1px solid #A3B78E" bgcolor="B8CEA8" valign="top" align="left" valign="top">
            <table width="165" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td><img src="<re%=approot%>/images/spacer.gif" width="1" height="13" /></td>
                </tr>
                <tr>
                    <td >
                        <div class="hovermenu"><ul>
                                <%if (menuIdx == 0) {%>
                                <la><a href="<%=approot%>/home.jsp?menu_idx=0">Home</a></la>
                                <%} else {%>
                                <li><a href="<%=approot%>/home.jsp?menu_idx=0">Home</a></li>                        
                                <%}%>                        
                        </ul></div>
                    </td> 
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                  <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spDaftarAkunPiutang || spDaftarAkunHutang || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
                <tr id="pinjaman1">
                    <td onClick="javascript:cmdChangeMenu('1')"> 
                        <div class="hovermenu"><ul><li><a href="javascript:cmdChangeMenu('1')">
                                        Pinjaman
                        </a></li></ul></div>                    
                    </td>
                </tr>
                <tr id="pinjaman2">
                    <td onClick="javascript:cmdChangeMenu('0')"> 
                        <div class="hovermenu"><ul><la><a href="javascript:cmdChangeMenu('0')">
                                        Pinjaman
                        </a></la></ul> </div>
                    </td>
                </tr>
                <tr id="pinjaman"> 
                    <td class="submenutd">
                        <table width="99%" cellpadding="0" cellspacing="0" class="submenu1">
                            <%if (spPinjamanKopBaru || spPinjamanKopAngsuran || spPinjamanKopArsip || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1">Pinjaman Koperasi</td>
                            </tr>
                            <%if (spPinjamanKopBaru) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/pinjaman/pinjamankopbankanuitas.jsp?menu_idx=1">Pinjaman 
                                Baru</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanKopAngsuran) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/pinjaman/angspinjamankop.jsp?menu_idx=1">Angsuran</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanKopArsip || spApprovePinjamanKop || spBayarPinjamanKop || spPelunasanPinjamanKop) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/pinjaman/arsippinjamankopbank.jsp?menu_idx=1">Arsip</a></td>
                            </tr>
                            <%}%>
                            <%}%>
                            
                            <%if (spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1">Pinjaman Anggota</td>
                            </tr>
                            <%if (spPinjamanAnggKopBaru || spPinjamanAnggKopAngsuran || spPinjamanAnggKopArsip || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg ) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><b>Pinjaman Ke Koperasi<b></td>
                            </tr>
                            <%if (spPinjamanAnggKopBaru) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/pinjaman.jsp?menu_idx=1">Pinjaman Baru</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanAnggKopAngsuran) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/srcbayarpinjaman.jsp?menu_idx=1">Angsuran</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanAnggKopArsip || spApprovePinjamanAngg || spBayarPinjamanAngg || spPelunasanPinjamanAngg ) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/arsippinjaman.jsp?menu_idx=1">Arsip</a></td>
                            </tr>
                            <%}%>
                            <%}%>
                            
                            <%if (spPinjamanAnggBankBaru || spPinjamanAnggBankAngsuran || spPinjamanAnggBankArsip || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu2">Pinjaman Bank</td>
                            </tr>
                            <%if (spPinjamanAnggBankBaru) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/pinjamanbankselect.jsp?menu_idx=1">Pinjaman Baru</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanAnggBankAngsuran) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/srcbayarpinjaman.jsp?menu_idx=1&src_type=1">Angsuran</a></td>
                            </tr>
                            <%}%>
                            <%if (spPinjamanAnggBankArsip || spApprovePinjamanAnggBank || spBayarPinjamanAnggBank || spPelunasanPinjamanBank) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu3"><a href="<%=approot%>/pinjaman/arsippinjamanbank.jsp?menu_idx=1">Arsip</a></td>
                            </tr>
                            <%}%>
                            <%}%>
                            <%}%>
                            <tr> 
                                <td height="18" width="90%"> </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                <%}%>
                
                
                <%if (spSimpananPeriodeRekap || spSimpananPeriodePotGaji || spSimpananBuku || spSimpananSaldo) {%>
                <tr id="simpanan1">
                    <td onClick="javascript:cmdChangeMenu('2')"> 
                        <div class="hovermenu"><ul><li><a href="javascript:cmdChangeMenu('2')">
                                        Proses Simpanan
                        </a></li></ul></div>                    
                    </td>
                </tr>
                <tr id="simpanan2">
                    <td onClick="javascript:cmdChangeMenu('0')"> 
                        <div class="hovermenu"><ul><la><a href="javascript:cmdChangeMenu('0')">
                                        Proses Simpanan
                        </a></la></ul> </div>
                    </td>
                </tr>                
                <tr id="simpanan"> 
                    <td class="submenutd">
                        <table width="99%" cellpadding="0" cellspacing="0" class="submenu1">
                            <%if (spSimpananPeriodeRekap) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/perioderekap.jsp?menu_idx=2">Periode 
                                Rekap</a></td>
                            </tr>
                            <%}%>
                            <%if (spSimpananPeriodePotGaji) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/rekappotongangaji.jsp?menu_idx=2">Rekap 
                                Potongan Gaji</a></td>
                            </tr>
                            <%}%>
                            <%if (spSimpananBuku) {%> 
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/srcmember.jsp?menu_idx=2">Buku 
                                Simpanan</a></td>
                            </tr>
                            <%}%>
                            <%if (spSimpananSaldo) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/saldosimpanan.jsp?menu_idx=2">Rekap 
                                Saldo Simpanan</a></td>
                            </tr>
                            <%}%>
                            <tr> 
                                <td height="18" width="90%"> </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                <%}%>      
                <%if (spLapSaldoHutang || spLapSaldoPiutang || spLapPembayaran) {%>
                <tr id="laporan1">
                    <td onClick="javascript:cmdChangeMenu('3')"> 
                        <div class="hovermenu"><ul><li><a href="javascript:cmdChangeMenu('3')">
                                        Laporan
                        </a></li></ul></div>                    
                    </td>
                </tr>
                <tr id="laporan2">
                    <td onClick="javascript:cmdChangeMenu('0')"> 
                        <div class="hovermenu"><ul><la><a href="javascript:cmdChangeMenu('0')">
                                        Laporan
                        </a></la></ul> </div>
                    </td>
                </tr>                 
                <tr id="laporan"> 
                    <td class="submenutd">
                        <table width="99%" cellpadding="0" cellspacing="0" class="submenu1">
                            <%if (spLapSaldoHutang) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/saldohutang.jsp?menu_idx=3">Saldo Hutang</a></td>
                            </tr>
                            <%}%>      
                            <%if (spLapSaldoPiutang) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/saldopiutang.jsp?menu_idx=3">Saldo 
                                Piutang</a></td>
                            </tr>
                            <%}%>      
                            <%if (spLapPembayaran) {%>
                            <tr> 
                                <td height="18" width="90%" class="menu1"><a href="<%=approot%>/pinjaman/rpt_pembayaran.jsp?menu_idx=3">Pembayaran</a></td>
                            </tr>
                            <%}%>      
                            <tr> 
                                <td height="18" width="90%"> </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                <%}%>
                <%if (spMasterAnggota) {%>
                <tr id="masterdata1">
                    <td onClick="javascript:cmdChangeMenu('4')"> 
                        <div class="hovermenu"><ul><li><a href="javascript:cmdChangeMenu('4')">
                                        Master Data
                        </a></li></ul></div>                    
                    </td>
                </tr>
                <tr id="masterdata2">
                    <td onClick="javascript:cmdChangeMenu('0')"> 
                        <div class="hovermenu"><ul><la><a href="javascript:cmdChangeMenu('0')">
                                        Master Data
                        </a></la></ul> </div>
                    </td>
                </tr>                 
                <tr id="masterdata"> 
                    <td class="submenutd">
                        <table width="99%" cellpadding="0" cellspacing="0" class="submenu1">
                            <%if (spMasterAnggota) {%>
                            <tr>
                                <td height="18" width="90%" class="menu1">Anggota</td>
                            </tr>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/member/scrmember.jsp?menu_idx=4">Daftar
                                Anggota</a></td>
                            </tr>
                            <tr> 
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/member/member.jsp?menu_idx=4">Anggota
                                Baru</a></td>
                            </tr>
                            <%}%>
                            <%if (true) {%>
                            <tr>
                                <td height="18" width="90%" class="menu1">Jenis Pinjaman</td>
                            </tr>
                            <tr>
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/pinjaman/scrjenispinjaman.jsp?menu_idx=4">Daftar
                                Jenis Pinjaman</a></td>
                            </tr>
                            <tr>
                                <td height="18" width="90%" class="menu2"><a href="<%=approot%>/pinjaman/jenispinjaman.jsp?menu_idx=4">Jenis Pinjaman
                                Baru</a></td>
                            </tr>
                            <%}%>
                            <tr> 
                                <td height="18" width="90%"> </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                <%}%>
                <%if (spDaftarPengguna || spPengelompokanPengguna) {%>
                <tr id="admin1">
                    <td onClick="javascript:cmdChangeMenu('5')"> 
                        <div class="hovermenu"><ul><li><a href="javascript:cmdChangeMenu('5')">
                                        Administrator
                        </a></li></ul></div>                    
                    </td>
                </tr>
                <tr id="admin2">
                    <td onClick="javascript:cmdChangeMenu('0')"> 
                        <div class="hovermenu"><ul><la><a href="javascript:cmdChangeMenu('0')">
                                        Administrator
                        </a></la></ul> </div>
                    </td>
                </tr>                
                <tr id="admin"> 
                    <td class="submenutd">
                        <table width="99%" cellpadding="0" cellspacing="0" class="submenu1">
                            <%if (spDaftarPengguna) {%>
                            <tr> 
                                <td width="80%" height="18" class="menu1"><a href="<%=approot%>/admin/userlist.jsp?menu_idx=5">Daftar Pengguna</a></td>
                            </tr>
                            <%}%>
                            <%if (spPengelompokanPengguna) {%>
                            <tr> 
                                <td width="80%" height="18" class="menu1"><a href="<%=approot%>/admin/grouplist.jsp?menu_idx=5">Pengelompokan Pengguna</a></td>
                            </tr>
                            <%}%>
                            <tr> 
                                <td height="18" width="90%"> </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td ><img src="<%=approot%>/imagessp/spacer.gif" width="1" height="2"></td>
                </tr>
                <%}%>                
                <tr>
                    <td >
                        <div class="hovermenu"><ul><li><a href="<%=approot%>/logoutsp.jsp">Logout</a></li></ul></div>
                    </td>
                </tr>                
                <tr> 
                    <td ><img src="<%=approot%>/images/spacer.gif" width="1" height="2"></td>
                </tr>                
                <tr> 
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script language="JavaScript">
    cmdChangeMenu('<%=menuIdx%>');
        </script>
