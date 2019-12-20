package year2016.day4;

import java.util.*;

public class Main {
    //private static final String INPUT = "qzmt-zixmtkozy-ivhz-343[zimth]";
    private static final String INPUT = "aczupnetwp-mfyyj-opalcexpye-977[peyac]\n" +
            "qzchnzbshud-cxd-trdq-sdrshmf-105[jqexn]\n" +
            "molgbzqfib-bdd-mrozexpfkd-289[bdfmo]\n" +
            "enzcntvat-pnaql-qrfvta-351[antqv]\n" +
            "otzkxtgzoutgr-jek-vaxingyotm-670[tgokx]\n" +
            "fmsledevhsyw-gerhc-gsexmrk-qerekiqirx-126[ersgh]\n" +
            "yknnkoera-zua-ykjpwejiajp-212[rfzym]\n" +
            "dfcxsqhwzs-qobrm-gvwddwbu-532[dwbqs]\n" +
            "oqnidbshkd-eknvdq-cdozqsldms-261[vygwn]\n" +
            "kwvacumz-ozilm-akidmvomz-pcvb-ikycqaqbqwv-538[mvaci]\n" +
            "sno-rdbqds-qzaahs-rsnqzfd-599[sdqan]\n" +
            "zekvierkzferc-treup-ivrthlzjzkzfe-633[ezrkf]\n" +
            "aoubshwq-suu-difqvogwbu-922[uboqs]\n" +
            "dpotvnfs-hsbef-kfmmzcfbo-nbobhfnfou-571[fbonh]\n" +
            "hcd-gsqfsh-qobrm-qcohwbu-gsfjwqsg-792[qsghb]\n" +
            "nbhofujd-cvooz-mbcpsbupsz-649[bocps]\n" +
            "pxtihgbsxw-ktuubm-tgterlbl-735[mxauz]\n" +
            "mvydjvxodqz-xviyt-hvivbzhzio-369[vizdh]\n" +
            "bqxnfdmhb-bzmcx-bnzshmf-cdozqsldms-755[bmdsz]\n" +
            "lxwbdvna-pajmn-snuuhknjw-mnbrpw-199[nwabj]\n" +
            "molgbzqfib-zxkav-zlxqfkd-ildfpqfzp-627[fzlqb]\n" +
            "iuruxlar-vrgyzoi-mxgyy-sgtgmksktz-904[gryik]\n" +
            "bkzrrhehdc-azrjds-qdzbpthrhshnm-729[hrdzb]\n" +
            "hplazytkpo-dnlgpyrpc-sfye-epnsyzwzrj-457[pyzel]\n" +
            "ajyqqgdgcb-hcjjwzcyl-umpiqfmn-366[cjqgm]\n" +
            "kzgwomvqk-kpwkwtibm-xczkpiaqvo-772[kwimo]\n" +
            "bqvvu-nwilwcejc-ydkykhwpa-ykjpwejiajp-628[jwkpy]\n" +
            "amlqskcp-epybc-afmamjyrc-pcqcypaf-574[capmy]\n" +
            "gcfcnuls-aluxy-zfiqyl-guleyncha-994[lcuya]\n" +
            "zgmfyxypbmsq-djmucp-umpiqfmn-444[qwrxz]\n" +
            "vrurcjah-pajmn-ljwmh-bnaerlnb-771[ajnrb]\n" +
            "nwlddtqtpo-awldetn-rcldd-opawzjxpye-457[ztsxm]\n" +
            "crwwv-bdd-rpbo-qbpqfkd-393[bdpqr]\n" +
            "dzczkrip-xiruv-srjbvk-rercpjzj-607[rjzci]\n" +
            "sno-rdbqds-dff-qdbdhuhmf-313[orjnb]\n" +
            "bnqqnrhud-bzmcx-qdrdzqbg-781[qbdnr]\n" +
            "upq-tfdsfu-kfmmzcfbo-tupsbhf-779[fubmp]\n" +
            "xgjougizobk-vrgyzoi-mxgyy-yzuxgmk-826[gyoxz]\n" +
            "zgmfyxypbmsq-zyqicr-bcnjmwkclr-756[jluaw]\n" +
            "joufsobujpobm-cvooz-vtfs-uftujoh-857[mghad]\n" +
            "lnkfaypeha-pkl-oaynap-xqjju-ykjpwejiajp-342[ajpky]\n" +
            "esyfwlau-wyy-vwhdgqewfl-580[pqlsd]\n" +
            "lhkhszqx-fqzcd-rbzudmfdq-gtms-trdq-sdrshmf-859[jzybf]\n" +
            "dmbttjgjfe-kfmmzcfbo-eftjho-623[kzlyx]\n" +
            "qcffcgwjs-xszzmpsob-zopcfohcfm-246[cfosz]\n" +
            "pbeebfvir-ovbunmneqbhf-cynfgvp-tenff-freivprf-273[jlves]\n" +
            "dpssptjwf-cvooz-sftfbsdi-727[sfdop]\n" +
            "vdzonmhydc-bzmcx-nodqzshnmr-391[dmnzc]\n" +
            "ynssr-vahvhetmx-tgterlbl-891[ldyzb]\n" +
            "zilqwikbqdm-akidmvomz-pcvb-lmdmtwxumvb-824[mbdiv]\n" +
            "qfkkj-nlyoj-xlcvpetyr-379[qsztm]\n" +
            "sbejpbdujwf-cvooz-nbobhfnfou-181[bofjn]\n" +
            "ipvohghykvbz-ihzrla-lunpullypun-383[jfptx]\n" +
            "clotzlnetgp-prr-lnbftdtetzy-665[tlenp]\n" +
            "fhezusjybu-rkddo-bqrehqjeho-894[ehbdj]\n" +
            "xlrypetn-ojp-lnbftdtetzy-327[uwdtq]\n" +
            "ovbunmneqbhf-pbafhzre-tenqr-enoovg-bcrengvbaf-299[benfo]\n" +
            "xmtjbzidx-zbb-gjbdnodxn-291[bdxjn]\n" +
            "raphhxuxts-ytaanqtpc-stktadebtci-739[tachp]\n" +
            "lejkrscv-avccpsvre-uvgcfpdvek-269[vtewy]\n" +
            "esyfwlau-xdgowj-umklgewj-kwjnauw-944[zntcg]\n" +
            "bnmrtldq-fqzcd-azrjds-rsnqzfd-261[dqrzf]\n" +
            "npmhcargjc-qaytclecp-fslr-yaosgqgrgml-184[cgalr]\n" +
            "iutyaskx-mxgjk-houngfgxjuay-vrgyzoi-mxgyy-lotgtiotm-280[reypq]\n" +
            "mhi-lxvkxm-cxeeruxtg-nlxk-mxlmbgz-891[ymnuv]\n" +
            "avw-zljyla-jovjvshal-dvyrzovw-331[vajlo]\n" +
            "jxdkbqfz-ciltbo-zlkqxfkjbkq-627[cvump]\n" +
            "kpvgtpcvkqpcn-lgnnadgcp-eqpvckpogpv-154[ikqst]\n" +
            "eadalsjq-yjsvw-hjgbwuladw-wyy-mkwj-lwklafy-944[wajly]\n" +
            "iuruxlar-iuxxuyobk-igtje-iutzgotsktz-644[uitxg]\n" +
            "pdjqhwlf-hjj-hqjlqhhulqj-179[hjqld]\n" +
            "drxevkzt-irsszk-kvtyefcfxp-997[kefrs]\n" +
            "houngfgxjuay-iuruxlar-inuiurgzk-vaxingyotm-644[yiowt]\n" +
            "lzfmdshb-bgnbnkzsd-dmfhmddqhmf-755[klhim]\n" +
            "mvkccspson-bkllsd-wkxkqowoxd-120[koscd]\n" +
            "rgndvtcxr-rpcsn-hpath-245[sitjk]\n" +
            "apuut-xviyt-nzmqdxzn-317[mgons]\n" +
            "udglrdfwlyh-vfdyhqjhu-kxqw-pdunhwlqj-829[dhlqu]\n" +
            "lejkrscv-gcrjkzt-xirjj-drerxvdvek-659[qftns]\n" +
            "zotts-mwupyhayl-bohn-uhufsmcm-604[imljo]\n" +
            "vcibutulxiom-jfumncw-alumm-mbcjjcha-370[mcuja]\n" +
            "nvrgfezqvu-tcrjjzwzvu-avccpsvre-ivjvrity-217[vrcjz]\n" +
            "myxcewob-qbkno-bkllsd-nocsqx-744[bockl]\n" +
            "zlilocri-pzxsbkdbo-erkq-ildfpqfzp-887[ilpzb]\n" +
            "zntargvp-pnaql-pbngvat-pbagnvazrag-377[agnpv]\n" +
            "rgndvtcxr-uadltg-prfjxhxixdc-921[xdrcg]\n" +
            "pbeebfvir-cynfgvp-tenff-ernpdhvfvgvba-663[fvebn]\n" +
            "bqvvu-ywjzu-ykwpejc-hwxknwpknu-290[wkujn]\n" +
            "xjmmjndqz-xviyt-xjvodib-vivgtndn-785[vdijn]\n" +
            "szfyrqriuflj-srjbvk-tljkfdvi-jvimztv-919[jvfir]\n" +
            "ucynmlgxcb-afmamjyrc-pcacgtgle-574[cagml]\n" +
            "zntargvp-pnaql-pbngvat-npdhvfvgvba-299[vanpg]\n" +
            "gcfcnuls-aluxy-xsy-jolwbumcha-110[cluas]\n" +
            "yuxufmdk-sdmpq-nuatmlmdpage-omzpk-oamfuzs-mzmxkeue-170[muade]\n" +
            "qfmcusbwq-pibbm-aobousasbh-792[bsamo]\n" +
            "zsxyfgqj-wfggny-qfgtwfytwd-515[lmnry]\n" +
            "rwcnawjcrxwju-ljwmh-lxjcrwp-anjlzdrbrcrxw-667[rwjcl]\n" +
            "ocipgvke-gii-vtckpkpi-466[ikpcg]\n" +
            "iruzfrtkzmv-treup-tfrkzex-kirzezex-945[rzekt]\n" +
            "drxevkzt-gcrjkzt-xirjj-glityrjzex-659[psznt]\n" +
            "htqtwkzq-gzssd-tujwfyntsx-125[tsqwz]\n" +
            "wbhsfbohwcboz-qobrm-igsf-hsghwbu-584[bhosw]\n" +
            "diozmivodjivg-wpiit-gvwjmvojmt-655[bknca]\n" +
            "vkppo-vbemuh-qsgkyiyjyed-478[yekpv]\n" +
            "enzcntvat-hafgnoyr-qlr-ratvarrevat-325[artnv]\n" +
            "atyzghrk-yigbktmkx-natz-yzuxgmk-644[inshw]\n" +
            "votubcmf-fhh-bobmztjt-415[nmolz]\n" +
            "pualyuhapvuhs-jhukf-jvhapun-shivyhavyf-773[asijb]\n" +
            "zovldbkfz-zxkav-zrpqljbo-pbosfzb-211[bdtuy]\n" +
            "ocipgvke-dcumgv-octmgvkpi-180[cgvik]\n" +
            "udglrdfwlyh-exqqb-ghyhorsphqw-595[hqdgl]\n" +
            "vhehkyne-vtgwr-vhtmbgz-ybgtgvbgz-657[gvbht]\n" +
            "bknsykmdsfo-bkwzkqsxq-oqq-ckvoc-458[sxmzy]\n" +
            "shoewudys-uww-tuiywd-426[wudsy]\n" +
            "krxqjijamxdb-kjbtnc-mnyjacvnwc-979[jcnab]\n" +
            "irdgrxzex-srjbvk-tljkfdvi-jvimztv-555[stqrm]\n" +
            "bxaxipgn-vgpst-rpcsn-pcpanhxh-869[pnxac]\n" +
            "tcrjjzwzvu-jtrmvexvi-ylek-jkfirxv-165[jvrei]\n" +
            "ugjjgkanw-vqw-vwkayf-476[qbskp]\n" +
            "sgmtkzoi-yigbktmkx-natz-iutzgotsktz-540[tkzgi]\n" +
            "wpuvcdng-dwppa-eqpvckpogpv-284[pvcdg]\n" +
            "tcfkqcevkxg-lgnnadgcp-ocpcigogpv-700[rcqwm]\n" +
            "tfejldvi-xiruv-avccpsvre-ivtvzmzex-503[stzno]\n" +
            "oknkvcta-itcfg-tcddkv-fgrnqaogpv-596[zcmwx]\n" +
            "udpsdjlqj-fdqgb-uhdftxlvlwlrq-491[hqpoz]\n" +
            "bpvctixr-ltpedcxots-rwdrdapit-bpcpvtbtci-687[eklrj]\n" +
            "kzgwomvqk-kivlg-bmkpvwtwog-590[kgvwm]\n" +
            "tcrjjzwzvu-tyftfcrkv-ivrthlzjzkzfe-607[ztfjr]\n" +
            "vqr-ugetgv-hnqygt-fgxgnqrogpv-440[gqvnr]\n" +
            "ahngzyzqcntr-azrjds-nodqzshnmr-417[khyzv]\n" +
            "gsrwyqiv-kvehi-hci-vigimzmrk-386[ivghk]\n" +
            "lgh-kwujwl-uzgugdslw-hmjuzskafy-450[umnbs]\n" +
            "xfbqpojafe-gmpxfs-bobmztjt-779[vztym]\n" +
            "lzfmdshb-eknvdq-rzkdr-339[dkrzb]\n" +
            "dmybmsuzs-qss-qzsuzqqduzs-690[sqzud]\n" +
            "nzwzcqfw-upwwjmply-opawzjxpye-925[wpzjy]\n" +
            "qvbmzvibqwvit-akidmvomz-pcvb-zmkmqdqvo-954[vmqbi]\n" +
            "ykjoqian-cnwza-oywrajcan-dqjp-hwxknwpknu-420[nawjk]\n" +
            "gzefmnxq-otaoaxmfq-efadmsq-846[sdilh]\n" +
            "dkqjcbctfqwu-lgnnadgcp-ujkrrkpi-830[ckdgj]\n" +
            "zilqwikbqdm-kivlg-kwibqvo-amzdqkma-980[ilkqv]\n" +
            "dszphfojd-ezf-qvsdibtjoh-883[dfhjo]\n" +
            "sno-rdbqds-idkkxadzm-btrsnldq-rdquhbd-339[utyem]\n" +
            "ckgvutofkj-igtje-iugzotm-giwaoyozout-514[ogtiu]\n" +
            "zekvierkzferc-sleep-jyzggzex-295[hjkop]\n" +
            "ktiaaqnqml-akidmvomz-pcvb-mvoqvmmzqvo-226[mvqao]\n" +
            "kyelcrga-qaytclecp-fslr-bcqgel-652[cleag]\n" +
            "pbybeshy-pnaql-pbngvat-qrirybczrag-715[bapry]\n" +
            "pbybeshy-onfxrg-qrfvta-611[bnziy]\n" +
            "glrcplyrgmlyj-zyqicr-qyjcq-782[yclqr]\n" +
            "nbhofujd-dboez-efqmpznfou-909[clnqa]\n" +
            "udskkaxawv-kusnwfywj-zmfl-hmjuzskafy-242[kafsu]\n" +
            "hjgbwuladw-vqw-esfsywewfl-476[lcezk]\n" +
            "tfejldvi-xiruv-wcfnvi-rercpjzj-477[ijrvc]\n" +
            "qyujihctyx-luvvcn-lyuwkocmcncih-162[cuyhi]\n" +
            "wlsiayhcw-wuhxs-wiuncha-uhufsmcm-266[qsifr]\n" +
            "wkqxodsm-oqq-kxkvicsc-926[kqcos]\n" +
            "tfcfiwlc-avccpsvre-rercpjzj-815[crefj]\n" +
            "sxdobxkdsyxkv-zvkcdsm-qbkcc-bokmaescsdsyx-614[lmhny]\n" +
            "ovbunmneqbhf-cynfgvp-tenff-nanylfvf-845[fnvbe]\n" +
            "chnylhuncihuf-wuhxs-lymyulwb-682[bsntk]\n" +
            "ckgvutofkj-igtje-jkbkruvsktz-436[kjtgu]\n" +
            "vhkkhlbox-ietlmbv-zktll-kxvxbobgz-267[bklvx]\n" +
            "oazegyqd-sdmpq-rxaiqd-iadwetab-898[adqei]\n" +
            "hdgdovmt-bmvyz-wpiit-hvivbzhzio-993[ljcbw]\n" +
            "nwlddtqtpo-tyepcyletzylw-ojp-cplnbftdtetzy-691[mdzsc]\n" +
            "ixccb-vfdyhqjhu-kxqw-whfkqrorjb-283[ichrd]\n" +
            "pelbtravp-pubpbyngr-qrcyblzrag-143[bprag]\n" +
            "kzgwomvqk-uiovmbqk-kivlg-abwziom-200[watbs]\n" +
            "vxupkizork-kmm-yzuxgmk-150[kmuxz]\n" +
            "xlrypetn-qwzhpc-epnsyzwzrj-847[pzenr]\n" +
            "egdytrixat-gpqqxi-gtrtxkxcv-661[txgiq]\n" +
            "lxuxaodu-lxwbdvna-pajmn-kjbtnc-mnbrpw-979[nabxd]\n" +
            "hqfxxnknji-kqtbjw-yjhmstqtld-125[bfzoy]\n" +
            "pyknyegle-hcjjwzcyl-bcnyprkclr-678[cylej]\n" +
            "rgndvtcxr-rpcsn-rdpixcv-gtprfjxhxixdc-791[rxcdp]\n" +
            "kyelcrga-djmucp-cleglccpgle-834[clegp]\n" +
            "oknkvcta-itcfg-hnqygt-tgceswkukvkqp-180[ktcgn]\n" +
            "yknnkoera-fahhuxawj-pnwejejc-784[aejnh]\n" +
            "jfifqxov-doxab-zxkav-zlxqfkd-lmboxqflkp-731[kreil]\n" +
            "tpspahyf-nyhkl-lnn-thyrlapun-435[vtsgw]\n" +
            "ktwbhtvmbox-vtgwr-mktbgbgz-631[btgkm]\n" +
            "hqfxxnknji-hfsid-htfynsl-htsyfnsrjsy-723[qymsp]\n" +
            "shmml-onfxrg-svanapvat-559[amnsv]\n" +
            "ugfkmewj-yjsvw-wyy-mkwj-lwklafy-138[wyjkf]\n" +
            "zilqwikbqdm-jiasmb-zmkmqdqvo-382[mqibd]\n" +
            "vqr-ugetgv-gii-ugtxkegu-414[gueit]\n" +
            "nuatmlmdpage-omzpk-oamfuzs-fqotzaxask-846[zpwto]\n" +
            "tfcfiwlc-irsszk-drerxvdvek-165[rcdef]\n" +
            "ykhknbqh-nwxxep-iwngapejc-368[nehkp]\n" +
            "zloolpfsb-tbxmlkfwba-oxyyfq-ixyloxqlov-887[loxbf]\n" +
            "surmhfwloh-mhoobehdq-zrunvkrs-127[horms]\n" +
            "uwtojhynqj-jll-wjhjnansl-437[jlnhw]\n" +
            "ziuxioqvo-ntwemz-lmaqov-824[oimqv]\n" +
            "yuxufmdk-sdmpq-nmewqf-abqdmfuaze-794[mdfqu]\n" +
            "nzwzcqfw-hplazytkpo-mfyyj-fdpc-epdetyr-951[pyfzc]\n" +
            "etyyx-eknvdq-zmzkxrhr-625[iltus]\n" +
            "oazegyqd-sdmpq-ngzzk-qzsuzqqduzs-300[osxtp]\n" +
            "eadalsjq-yjsvw-kusnwfywj-zmfl-ghwjslagfk-814[jswaf]\n" +
            "yuxufmdk-sdmpq-nmewqf-emxqe-378[rqsbf]\n" +
            "iwcjapey-ywjzu-odellejc-888[ejclw]\n" +
            "pdjqhwlf-gbh-ilqdqflqj-413[qldfh]\n" +
            "ide-htrgti-snt-hpath-479[thiad]\n" +
            "bnknqetk-azrjds-sdbgmnknfx-209[nkbds]\n" +
            "tfcfiwlc-irdgrxzex-wcfnvi-uvgcfpdvek-555[cfivd]\n" +
            "clxalrtyr-ojp-epnsyzwzrj-249[rjlpy]\n" +
            "aietsrmdih-veffmx-gywxsqiv-wivzmgi-360[imvef]\n" +
            "fkqbokxqflkxi-zelzlixqb-cfkxkzfkd-549[afuiz]\n" +
            "zekvierkzferc-upv-dribvkzex-347[ekrvz]\n" +
            "pbafhzre-tenqr-onfxrg-ybtvfgvpf-507[yrjit]\n" +
            "willimcpy-vohhs-guleyncha-240[swucm]\n" +
            "gpewwmjmih-veffmx-pefsvexsvc-360[ryciz]\n" +
            "amjmpdsj-zsllw-umpiqfmn-418[itmwe]\n" +
            "myxcewob-qbkno-lkcuod-mecdywob-cobfsmo-666[obcmd]\n" +
            "zsxyfgqj-uqfxynh-lwfxx-fhvznxnynts-515[zymab]\n" +
            "zovldbkfz-tbxmlkfwba-ciltbo-tlohpelm-237[ijhlk]\n" +
            "nwilwcejc-nwxxep-zalwnpiajp-992[wnpac]\n" +
            "amppmqgtc-qaytclecp-fslr-qyjcq-210[sytev]\n" +
            "wsvsdkbi-qbkno-cmkfoxqob-rexd-bokmaescsdsyx-302[sbkod]\n" +
            "hcd-gsqfsh-xszzmpsob-fsgsofqv-480[sfgho]\n" +
            "wfummczcyx-mwupyhayl-bohn-nywbhifias-318[yhmwa]\n" +
            "ygcrqpkbgf-fag-tgceswkukvkqp-154[gkcfp]\n" +
            "hqcfqwydw-uww-iuhlysui-894[dcqnf]\n" +
            "mvydjvxodqz-xviyt-rjmfncjk-421[jvdmx]\n" +
            "froruixo-hjj-vhuylfhv-569[hfjor]\n" +
            "froruixo-hjj-ghyhorsphqw-855[horjf]\n" +
            "vdzonmhydc-eknvdq-qdbdhuhmf-157[dhmnq]\n" +
            "cxy-bnlanc-bljenwpna-qdwc-bqryyrwp-901[nbcwy]\n" +
            "ocipgvke-rncuvke-itcuu-tgegkxkpi-284[kcegi]\n" +
            "npmhcargjc-afmamjyrc-rpyglgle-600[acgmr]\n" +
            "npmhcargjc-qaytclecp-fslr-mncpyrgmlq-262[clmpr]\n" +
            "sgmtkzoi-xghhoz-sgtgmksktz-176[gkstz]\n" +
            "ohmnuvfy-xsy-fiacmncwm-162[mcfny]\n" +
            "xekdwvwnzkqo-acc-ykjpwejiajp-706[jkwac]\n" +
            "gvcskirmg-tpewxmg-kveww-erepcwmw-256[hvuwt]\n" +
            "esyfwlau-usfvq-wfyafwwjafy-398[icojl]\n" +
            "ujoon-gpqqxi-rjhidbtg-htgkxrt-349[gthij]\n" +
            "votubcmf-ezf-sfbdrvjtjujpo-571[fjbot]\n" +
            "zixppfcfba-zelzlixqb-mrozexpfkd-549[yknmt]\n" +
            "fbebmtkr-zktwx-utldxm-nlxk-mxlmbgz-423[mxbkl]\n" +
            "pybgmyargtc-glrcplyrgmlyj-aylbw-amyrgle-qcptgacq-964[tszdw]\n" +
            "sebehvkb-vbemuh-iqbui-920[behiu]\n" +
            "lugjuacha-zfiqyl-guleyncha-292[xtqyp]\n" +
            "jsehsyafy-wyy-esfsywewfl-190[oztvg]\n" +
            "esyfwlau-tskcwl-kzahhafy-918[afhkl]\n" +
            "nzwzcqfw-mfyyj-nfdezxpc-dpcgtnp-717[yoapc]\n" +
            "dszphfojd-fhh-efqmpznfou-259[igfar]\n" +
            "ajmrxjlcren-kjbtnc-xynajcrxwb-823[jcnrx]\n" +
            "vehmsegxmzi-nippcfier-pefsvexsvc-594[eipsv]\n" +
            "dlhwvupglk-yhiipa-klzpnu-747[taxwo]\n" +
            "frqvxphu-judgh-udglrdfwlyh-mhoobehdq-fxvwrphu-vhuylfh-257[mswnl]\n" +
            "mvkccspson-pvygob-nocsqx-718[cosnp]\n" +
            "avw-zljyla-msvdly-klwhyatlua-955[layvw]\n" +
            "ryexqpqhteki-sxesebqju-iqbui-140[tyxfz]\n" +
            "ygcrqpkbgf-ecpfa-eqcvkpi-tgceswkukvkqp-856[kcpeg]\n" +
            "oazegyqd-sdmpq-nmewqf-ymdwqfuzs-560[qdmef]\n" +
            "qxdwpopgsdjh-qphzti-gtprfjxhxixdc-323[pxdhg]\n" +
            "zloolpfsb-zxkav-obxznrfpfqflk-783[flozb]\n" +
            "xgvnndadzy-kgvnodx-bmvnn-vxlpdndodji-473[xbwpm]\n" +
            "muqfedyput-sqdto-vydqdsydw-322[kqmys]\n" +
            "jlidywncfy-mwupyhayl-bohn-lywycpcha-838[ychlw]\n" +
            "votubcmf-cvooz-mphjtujdt-285[otcjm]\n" +
            "etaqigpke-dcumgv-tgugctej-778[getcu]\n" +
            "amlqskcp-epybc-pyzzgr-mncpyrgmlq-496[veyij]\n" +
            "excdklvo-pvygob-kxkvicsc-380[ckvox]\n" +
            "bjfutsneji-idj-xmnuunsl-437[jnuis]\n" +
            "crwwv-avb-zlkqxfkjbkq-835[kbqvw]\n" +
            "clxalrtyr-nlyoj-nzletyr-dstaatyr-561[lrtya]\n" +
            "bjfutsneji-uqfxynh-lwfxx-ijxnls-957[uaybk]\n" +
            "drxevkzt-gcrjkzt-xirjj-rthlzjzkzfe-737[zjrkt]\n" +
            "lahxpnwrl-npp-jwjuhbrb-329[pbhjl]\n" +
            "jyddc-nippcfier-hitevxqirx-646[pyzmv]\n" +
            "tagzsrsjvgmk-jsttal-klgjsyw-398[jtkyl]\n" +
            "kwzzwaqdm-ntwemz-lmxizbumvb-148[mzwba]\n" +
            "jyddc-gerhc-gsexmrk-vigimzmrk-906[zmtql]\n" +
            "oqnidbshkd-atmmx-bnmszhmldms-729[vcsyn]\n" +
            "hcd-gsqfsh-foppwh-rsjszcdasbh-558[shcdf]\n" +
            "fydelmwp-mfyyj-cpnptgtyr-353[udkrq]\n" +
            "willimcpy-mwupyhayl-bohn-mniluay-214[lyima]\n" +
            "nzwzcqfw-nlyoj-nzletyr-cplnbftdtetzy-925[blcjr]\n" +
            "ytu-xjhwjy-uqfxynh-lwfxx-wjfhvznxnynts-567[xnyfh]\n" +
            "lejkrscv-vxx-vexzevvizex-373[tgvkh]\n" +
            "rnqnyfwd-lwfij-hfsid-htfynsl-ijajqturjsy-229[anmsk]\n" +
            "chnylhuncihuf-vumeyn-mylpcwym-162[naygw]\n" +
            "xjinphzm-bmvyz-agjrzm-hvmfzodib-239[mzbhi]\n" +
            "elrkdcdugrxv-mhoobehdq-vwrudjh-751[dhreo]\n" +
            "mbggf-kfl-zopwwpun-721[fzbwt]\n" +
            "zilqwikbqdm-rmttgjmiv-mvoqvmmzqvo-954[mqvio]\n" +
            "qczcftiz-rms-aofyshwbu-610[cfsza]\n" +
            "ibghopzs-qvcqczohs-rsjszcdasbh-272[usani]\n" +
            "mbiyqoxsm-mkxni-mykdsxq-oxqsxoobsxq-146[zplsb]\n" +
            "zekvierkzferc-irsszk-uvgrikdvek-191[keriv]\n" +
            "wkqxodsm-lkcuod-nozvyiwoxd-328[odkwx]\n" +
            "frqvxphu-judgh-edvnhw-sxufkdvlqj-387[csdlt]\n" +
            "kwvacumz-ozilm-lgm-abwziom-668[mzail]\n" +
            "vdzonmhydc-cxd-rdquhbdr-209[dchrb]\n" +
            "molgbzqfib-mixpqfz-doxpp-qoxfkfkd-939[fopqx]\n" +
            "xzwrmkbqtm-xtiabqk-oziaa-camz-bmabqvo-642[goucj]\n" +
            "houngfgxjuay-yigbktmkx-natz-xkykgxin-228[fhiyr]\n" +
            "oxmeeuruqp-pkq-abqdmfuaze-612[equam]\n" +
            "gpewwmjmih-tpewxmg-kveww-jmrergmrk-542[mwegr]\n" +
            "pelbtravp-qlr-grpuabybtl-143[byozf]\n" +
            "cqwdujys-rkddo-iqbui-114[hdywo]\n" +
            "gpsxdprixkt-tvv-gtprfjxhxixdc-817[xptdg]\n" +
            "amlqskcp-epybc-aylbw-amyrgle-qfgnngle-964[laegy]\n" +
            "yuxufmdk-sdmpq-eomhqzsqd-tgzf-qzsuzqqduzs-482[tlzym]\n" +
            "plolwdub-judgh-fdqgb-frdwlqj-uhdftxlvlwlrq-127[izfao]\n" +
            "egdytrixat-ytaanqtpc-detgpixdch-505[tadce]\n" +
            "awzwhofm-ufors-gqojsbusf-vibh-difqvogwbu-922[phblt]\n" +
            "hafgnoyr-ohaal-chepunfvat-221[ahfno]\n" +
            "vagreangvbany-cynfgvp-tenff-chepunfvat-559[nafve]\n" +
            "tcorcikpi-ecpfa-eqcvkpi-wugt-vguvkpi-388[cipkv]\n" +
            "zloolpfsb-zlkprjbo-doxab-zxkav-zlxqfkd-obzbfsfkd-757[bozfk]\n" +
            "ykhknbqh-ywjzu-ykjpwejiajp-966[jkyhp]\n" +
            "kwvacumz-ozilm-moo-aitma-512[maoiz]\n" +
            "wfruflnsl-nsyjwsfyntsfq-xhfajsljw-mzsy-jslnsjjwnsl-229[sjfln]\n" +
            "sno-rdbqds-okzrshb-fqzrr-nodqzshnmr-781[xqzdf]\n" +
            "yrwxefpi-tvsnigxmpi-veffmx-stivexmsrw-724[ixefm]\n" +
            "hqtyeqsjylu-isqludwuh-xkdj-huiuqhsx-712[uhqsd]\n" +
            "yhkpvhjapcl-ibuuf-zavyhnl-903[halpu]\n" +
            "forwcoqhwjs-pogysh-gvwddwbu-818[wodgh]\n" +
            "oqnidbshkd-dff-nodqzshnmr-573[ysdzb]\n" +
            "gzefmnxq-dmnnuf-dqeqmdot-482[dmnqe]\n" +
            "lnkfaypeha-oywrajcan-dqjp-zaoecj-576[ajcen]\n" +
            "xtwtelcj-rclop-mldvpe-afcnsldtyr-899[dxnhp]\n" +
            "rmn-qcapcr-hcjjwzcyl-umpiqfmn-522[cmjnp]\n" +
            "egdytrixat-gpqqxi-tcvxcttgxcv-271[txcgi]\n" +
            "njmjubsz-hsbef-fhh-tfswjdft-103[fhjsb]\n" +
            "ugjjgkanw-tmffq-umklgewj-kwjnauw-996[jwgku]\n" +
            "gifavtkzcv-gcrjkzt-xirjj-glityrjzex-737[jgirt]\n" +
            "bgmxkgtmbhgte-unggr-xgzbgxxkbgz-137[gbxkm]\n" +
            "xgvnndadzy-agjrzm-kpmxcvndib-317[yqtlw]\n" +
            "nwilwcejc-zua-ykjpwejiajp-758[jwace]\n" +
            "ykhknbqh-oywrajcan-dqjp-naoawnyd-264[anydh]\n" +
            "iutyaskx-mxgjk-yigbktmkx-natz-yzuxgmk-904[imkry]\n" +
            "vhkkhlbox-vahvhetmx-kxlxtkva-163[hkvxa]\n" +
            "ktiaaqnqml-moo-amzdqkma-148[gklmn]\n" +
            "shoewudys-sxesebqju-kiuh-juijydw-998[suejd]\n" +
            "houngfgxjuay-ckgvutofkj-pkrrehkgt-cuxqynuv-410[ugkcf]\n" +
            "aczupnetwp-mldvpe-xlcvpetyr-249[mcajd]\n" +
            "zvyvgnel-tenqr-enzcntvat-wryylorna-znexrgvat-507[nertv]\n" +
            "dyz-combod-oqq-bocokbmr-250[yvlka]\n" +
            "qmpmxevc-kvehi-tpewxmg-kveww-qerekiqirx-880[eikmq]\n" +
            "rflsjynh-xhfajsljw-mzsy-xmnuunsl-411[cpyxn]\n" +
            "ibghopzs-qobrm-qcohwbu-qcbhowbasbh-740[yajfh]\n" +
            "pinovwgz-zbb-yzndbi-291[rqzob]\n" +
            "emixwvqhml-kivlg-ivitgaqa-278[ivagl]\n" +
            "jchipqat-rpcsn-rdpixcv-jhtg-ithixcv-635[cihpt]\n" +
            "xtwtelcj-rclop-ojp-wlmzclezcj-275[cljeo]\n" +
            "cybyjqho-whqtu-sxesebqju-huqsgkyiyjyed-712[yqehj]\n" +
            "htqtwkzq-xhfajsljw-mzsy-knsfshnsl-489[gbywx]\n" +
            "ncjzrpytn-ojp-xlcvpetyr-405[pcjnr]\n" +
            "enqvbnpgvir-onfxrg-ybtvfgvpf-455[qsbad]\n" +
            "xgvnndadzy-ytz-yzndbi-967[dnyza]\n" +
            "lujbbrornm-kdwwh-orwjwlrwp-615[wrbjl]\n" +
            "yhwooebeaz-ydkykhwpa-bejwjyejc-368[eyjwa]\n" +
            "hqtyeqsjylu-sxesebqju-tulubefcudj-894[sizkn]\n" +
            "zbytomdsvo-mrymyvkdo-crszzsxq-458[mosyz]\n" +
            "ojk-nzxmzo-agjrzm-yzndbi-343[zjmno]\n" +
            "bdavqofuxq-oazegyqd-sdmpq-omzpk-oamfuzs-fdmuzuzs-664[zdmoq]\n" +
            "dfcxsqhwzs-qobrm-fsoqeiwgwhwcb-792[wqsbc]\n" +
            "dpssptjwf-kfmmzcfbo-sfbdrvjtjujpo-649[fjpsb]\n" +
            "rgndvtcxr-tvv-detgpixdch-297[xnstm]\n" +
            "tmrszakd-cxd-kzanqzsnqx-235[zadkn]\n" +
            "zhdsrqlchg-fdqgb-frdwlqj-ghsduwphqw-725[dhqgw]\n" +
            "tyepcyletzylw-dnlgpyrpc-sfye-pyrtyppctyr-405[yptce]\n" +
            "ynukcajey-xwogap-zalhkuiajp-524[sgmwy]\n" +
            "eadalsjq-yjsvw-kusnwfywj-zmfl-sfsdqkak-190[safjk]\n" +
            "clotzlnetgp-ojp-cpdplcns-899[pclno]\n" +
            "pbybeshy-onfxrg-phfgbzre-freivpr-507[rbefp]\n" +
            "hwbba-rncuvke-itcuu-yqtmujqr-414[ubcqr]\n" +
            "uiovmbqk-xtiabqk-oziaa-wxmzibqwva-902[aibqk]\n" +
            "kgjgrypw-epybc-qaytclecp-fslr-pcacgtgle-288[cgpel]\n" +
            "dmybmsuzs-rxaiqd-fdmuzuzs-664[dmsuz]\n" +
            "jsvagsulanw-kusnwfywj-zmfl-esfsywewfl-528[swfla]\n" +
            "jvuzbtly-nyhkl-ibuuf-mpuhujpun-149[ubhjl]\n" +
            "zsxyfgqj-gfxpjy-xjwanhjx-307[jxfgy]\n" +
            "wifilzof-wuhxs-womnigyl-mylpcwy-448[wilyf]\n" +
            "dwbcjkun-kjbtnc-ujkxajcxah-537[jckab]\n" +
            "ucynmlgxcb-afmamjyrc-pcyaosgqgrgml-652[cgmay]\n" +
            "gpewwmjmih-mrxivrexmsrep-veffmx-pefsvexsvc-490[emvxf]\n" +
            "wifilzof-vumeyn-mbcjjcha-682[cfijm]\n" +
            "ujqgywfau-bwddqtwsf-ljsafafy-658[fawdj]\n" +
            "ujqgywfau-usfvq-ugslafy-suimakalagf-788[aufgs]\n" +
            "lgh-kwujwl-jsttal-ogjckzgh-294[gjlhk]\n" +
            "votubcmf-qmbtujd-hsbtt-sfdfjwjoh-909[tbfjd]\n" +
            "zntargvp-wryylorna-qrcnegzrag-221[ragny]\n" +
            "nvrgfezqvu-jtrmvexvi-ylek-nfibjyfg-763[duyon]\n" +
            "myvybpev-oqq-crszzsxq-276[qsvyz]\n" +
            "nzcczdtgp-nlyoj-nzletyr-nfdezxpc-dpcgtnp-535[ncpzd]\n" +
            "wyvqljapsl-jovjvshal-svnpzapjz-877[jvalp]\n" +
            "lxuxaodu-lqxlxujcn-bqryyrwp-381[xluqr]\n" +
            "iuxxuyobk-jek-iayzuskx-ykxboik-826[kxiuy]\n" +
            "dmybmsuzs-omzpk-oamfuzs-efadmsq-690[mszad]\n" +
            "ymszqfuo-dmnnuf-pqbmdfyqzf-300[fmqdn]\n" +
            "xfbqpojafe-cvooz-xpsltipq-649[opfqx]\n" +
            "mvkccspson-bknsykmdsfo-oqq-domrxyvyqi-536[oskmq]\n" +
            "ykhknbqh-zua-iwjwcaiajp-420[ahijk]\n" +
            "pbafhzre-tenqr-rtt-phfgbzre-freivpr-299[docru]\n" +
            "pynffvsvrq-enoovg-fnyrf-585[fnvor]\n" +
            "oaddaeuhq-qss-efadmsq-794[xjkyr]\n" +
            "oaxadrgx-dmpuamofuhq-qss-oazfmuzyqzf-586[mfaud]\n" +
            "yaxsnlcrun-ljwmh-ldbcxvna-bnaerln-173[nlabc]\n" +
            "ksodcbwnsr-qcffcgwjs-qobrm-igsf-hsghwbu-714[sbcfg]\n" +
            "chnylhuncihuf-vohhs-xypyfijgyhn-136[yzkfs]\n" +
            "zgmfyxypbmsq-aylbw-dglylagle-236[lygab]\n" +
            "zadftbaxq-anvqof-efadmsq-482[afqdb]\n" +
            "jrncbavmrq-cynfgvp-tenff-fuvccvat-325[cfvna]\n" +
            "veqtekmrk-fyrrc-xiglrspskc-880[tscqj]\n" +
            "jsehsyafy-usfvq-ogjckzgh-814[sfghj]\n" +
            "zilqwikbqdm-zijjqb-ivitgaqa-850[pjgiu]\n" +
            "upq-tfdsfu-cvooz-dvtupnfs-tfswjdf-519[fdstu]\n" +
            "dszphfojd-qmbtujd-hsbtt-ufdiopmphz-441[dhptb]\n" +
            "bkwzkqsxq-pvygob-cdybkqo-198[bkqoy]\n" +
            "nzcczdtgp-clmmte-ecltytyr-275[ctelm]\n" +
            "vehmsegxmzi-gerhc-wxsveki-308[eghim]\n" +
            "hvbizodx-xjgjmapg-kgvnodx-bmvnn-vivgtndn-421[museg]\n" +
            "oxmeeuruqp-rxaiqd-mocgueufuaz-508[tysvw]\n" +
            "vagreangvbany-pnaql-znantrzrag-559[angrv]\n" +
            "qspkfdujmf-dboez-dpbujoh-qvsdibtjoh-441[dbjof]\n" +
            "nzydfxpc-rclop-qwzhpc-pyrtyppctyr-847[bdick]\n" +
            "iuruxlar-hgyqkz-xkikobotm-618[kioru]\n" +
            "zilqwikbqdm-akidmvomz-pcvb-camz-bmabqvo-512[mbaiq]\n" +
            "ohmnuvfy-wuhxs-wiuncha-nywbhifias-136[hinuw]\n" +
            "myvybpev-mrymyvkdo-nozvyiwoxd-770[sinbh]\n" +
            "sorozgxe-mxgjk-xghhoz-rghuxgzuxe-904[gxhoz]\n" +
            "ujoon-rpcsn-detgpixdch-661[cdnop]\n" +
            "nzwzcqfw-awldetn-rcldd-hzcvdsza-561[dzcwa]\n" +
            "oknkvcta-itcfg-eqpuwogt-itcfg-dwppa-gpikpggtkpi-830[gptik]\n" +
            "hcd-gsqfsh-suu-rsdofhasbh-454[shdfu]\n" +
            "aczupnetwp-mldvpe-hzcvdsza-613[pzacd]\n" +
            "hwbba-lgnnadgcp-fgrnqaogpv-128[xzspm]\n" +
            "drxevkzt-vxx-dribvkzex-581[xvdek]\n" +
            "esyfwlau-usfvq-ghwjslagfk-710[fsagl]\n" +
            "zlkprjbo-doxab-pzxsbkdbo-erkq-obxznrfpfqflk-861[bkofp]\n" +
            "mbiyqoxsm-mkxni-psxkxmsxq-536[xmsik]\n" +
            "wihmogyl-aluxy-lugjuacha-wuhxs-uwkocmcncih-942[uchal]\n" +
            "aoubshwq-gqojsbusf-vibh-difqvogwbu-116[boqsu]\n" +
            "tfejldvi-xiruv-vxx-fgvirkzfej-321[vfixe]\n" +
            "eqpuwogt-itcfg-hnqygt-uvqtcig-128[gtqci]\n" +
            "forwcoqhwjs-foppwh-gozsg-376[owfgh]\n" +
            "xjmmjndqz-wpiit-yzndbi-447[idjmn]\n" +
            "ixccb-exqqb-ghsduwphqw-595[zxtyc]\n" +
            "ynukcajey-ywjzu-hwxknwpknu-186[tsayq]\n" +
            "jlidywncfy-mwupyhayl-bohn-guhuaygyhn-396[yhnua]\n" +
            "wyvqljapsl-jovjvshal-lunpullypun-721[ljpuv]\n" +
            "surmhfwloh-fdqgb-frdwlqj-fxvwrphu-vhuylfh-413[fhlru]\n" +
            "cybyjqho-whqtu-kdijqrbu-sxesebqju-tulubefcudj-374[ubjqe]\n" +
            "hqtyeqsjylu-vbemuh-fkhsxqiydw-842[inepa]\n" +
            "rdggdhxkt-hrpktcvtg-wjci-apqdgpidgn-557[ahtds]\n" +
            "bnknqetk-bgnbnkzsd-dmfhmddqhmf-339[dnbkm]\n" +
            "bqxnfdmhb-cxd-bnmszhmldms-131[mbdhn]\n" +
            "jsehsyafy-usfvq-ugslafy-jwsuimakalagf-788[asfuy]\n" +
            "ltpedcxots-rpcsn-rdpixcv-hwxeexcv-609[cxepd]\n" +
            "ojk-nzxmzo-pinovwgz-agjrzm-hvmfzodib-915[scjyr]\n" +
            "krxqjijamxdb-kdwwh-jwjuhbrb-849[jbwdh]\n" +
            "sbejpbdujwf-cbtlfu-vtfs-uftujoh-285[dsgnt]\n" +
            "dkqjcbctfqwu-dwppa-fgukip-622[pcdfk]\n" +
            "cvabijtm-kivlg-kwibqvo-aitma-200[iavbk]\n" +
            "dsxxw-aylbw-dglylagle-652[gsakl]\n" +
            "gpbepvxcv-gpsxdprixkt-eaphixr-vgphh-htgkxrth-453[phxgr]\n" +
            "bnqqnrhud-rbzudmfdq-gtms-sdbgmnknfx-365[dnbmq]\n" +
            "etyyx-qzaahs-lzqjdshmf-547[ahqsy]\n" +
            "kpvgtpcvkqpcn-lgnnadgcp-ujkrrkpi-154[pkcgn]\n" +
            "cvabijtm-ntwemz-bmkpvwtwog-226[mtwbv]\n" +
            "guahyncw-mwupyhayl-bohn-uwkocmcncih-864[chnuw]\n" +
            "aczupnetwp-mldvpe-nzyeltyxpye-171[bwvxh]\n" +
            "ryexqpqhteki-sqdto-seqjydw-jhqydydw-920[qdyeh]\n" +
            "aietsrmdih-hci-vigimzmrk-568[imhra]\n" +
            "zilqwikbqdm-lgm-xczkpiaqvo-356[iqklm]\n" +
            "zvyvgnel-tenqr-pubpbyngr-npdhvfvgvba-533[vnbgp]\n" +
            "vrurcjah-pajmn-lxuxaodu-kdwwh-mnbrpw-875[fnqea]\n" +
            "mrxivrexmsrep-jpsaiv-irkmriivmrk-958[rimve]\n" +
            "mvhkvbdib-wvnfzo-omvdidib-733[stayi]\n" +
            "ryexqpqhteki-kdijqrbu-zubboruqd-fkhsxqiydw-192[tsdpw]\n" +
            "oxjmxdfkd-zxkav-zlxqfkd-xkxivpfp-497[smpbo]\n" +
            "cebwrpgvyr-pnaql-znantrzrag-117[rangp]\n" +
            "qfmcusbwq-qvcqczohs-obozmgwg-766[qcobg]\n" +
            "dmbttjgjfe-ezf-bdrvjtjujpo-129[gbjwt]\n" +
            "rflsjynh-hmthtqfyj-zxjw-yjxynsl-593[jyhfl]\n" +
            "ncjzrpytn-awldetn-rcldd-afcnsldtyr-769[ntqrm]\n" +
            "pdjqhwlf-fdqgb-frdwlqj-vwrudjh-699[dfjqw]\n" +
            "slqryzjc-zsllw-yaosgqgrgml-210[lgsqr]\n" +
            "bkwzkqsxq-oqq-vyqscdsmc-718[qsckb]\n" +
            "lsyrkjkbnyec-oqq-vklybkdybi-224[kyblq]\n" +
            "vkppo-rqiauj-skijecuh-iuhlysu-530[uihjk]\n" +
            "guahyncw-wifilzof-zfiqyl-xyjfisgyhn-526[uhsvy]\n" +
            "gsvvswmzi-hci-viwievgl-984[ivgsw]\n" +
            "ftzgxmbv-vahvhetmx-vnlmhfxk-lxkobvx-345[vxhmb]\n" +
            "eqttqukxg-hnqygt-rwtejcukpi-882[tqegk]\n" +
            "ygcrqpkbgf-gii-rwtejcukpi-674[gickp]\n" +
            "ibghopzs-pogysh-qcbhowbasbh-558[bhosg]\n" +
            "ocipgvke-uecxgpigt-jwpv-ncdqtcvqta-544[cgptv]\n" +
            "drxevkzt-sleep-ivjvrity-685[tkwzb]\n" +
            "qekrixmg-gsvvswmzi-jpsaiv-hitpscqirx-256[isvgm]\n" +
            "qjopwxha-bhksan-lqnydwoejc-862[ahjno]\n" +
            "vjpwncrl-ljwmh-mnbrpw-277[wjlmn]\n" +
            "zbytomdsvo-tovvilokx-nofovyzwoxd-796[ysvjp]\n" +
            "ajyqqgdgcb-aylbw-amyrgle-pcacgtgle-522[gacly]\n" +
            "zixppfcfba-zxkav-abpfdk-835[afpbk]\n" +
            "gzefmnxq-oaddaeuhq-nmewqf-oazfmuzyqzf-924[fqzae]\n" +
            "drxevkzt-sleep-glityrjzex-841[elrtx]\n" +
            "yflexwxoalrp-yrkkv-zrpqljbo-pbosfzb-367[blopr]\n" +
            "lugjuacha-dyffsvyuh-omyl-nymncha-578[yahuc]\n" +
            "tpspahyf-nyhkl-jovjvshal-aljouvsvnf-175[vahjl]\n" +
            "htwwtxnaj-rnqnyfwd-lwfij-jll-wjfhvznxnynts-567[nwjfl]\n" +
            "tipfxvezt-avccpsvre-ivjvrity-503[vitce]\n" +
            "myvybpev-bkllsd-yzobkdsyxc-198[ybdkl]\n" +
            "cqwdujys-sqdto-seqjydw-vydqdsydw-348[asypt]\n" +
            "bqxnfdmhb-qzaahs-otqbgzrhmf-339[hgfsm]\n" +
            "jshzzpmplk-lnn-aljouvsvnf-175[lnjps]\n" +
            "jxdkbqfz-zlilocri-pzxsbkdbo-erkq-zlkqxfkjbkq-679[kbqzl]\n" +
            "irgyyolokj-hatte-xkikobotm-488[oktiy]\n" +
            "buzahisl-jhukf-zavyhnl-123[haluz]\n" +
            "odiih-lqxlxujcn-orwjwlrwp-823[qoruw]\n" +
            "vhehkyne-utldxm-kxtvjnblbmbhg-293[bhekl]\n" +
            "fmsledevhsyw-gerhc-gsexmrk-vieguymwmxmsr-282[emsgr]\n" +
            "xgsvgmotm-igtje-lotgtiotm-852[qsejd]\n" +
            "xfbqpojafe-cbtlfu-tbmft-311[fbtac]\n" +
            "zlkprjbo-doxab-mixpqfz-doxpp-obzbfsfkd-601[bopdf]\n" +
            "szfyrqriuflj-srjbvk-wzeretzex-789[rezfj]\n" +
            "xjgjmapg-pinovwgz-xviyt-hvivbzhzio-759[wlrju]\n" +
            "hjgbwuladw-usfvq-ghwjslagfk-918[gwafh]\n" +
            "pbeebfvir-pubpbyngr-fgbentr-221[beprf]\n" +
            "ujqgywfau-usfvq-ogjckzgh-164[gufjq]\n" +
            "fkqbokxqflkxi-bdd-pqloxdb-497[bdkqx]\n" +
            "froruixo-sodvwlf-judvv-vdohv-855[aijkw]\n" +
            "oxaflxzqfsb-gbiivybxk-obzbfsfkd-601[bfxik]\n" +
            "gsvvswmzi-gerhc-gsexmrk-hiwmkr-594[gmrse]\n" +
            "xzwrmkbqtm-akidmvomz-pcvb-uizsmbqvo-252[wfzme]\n" +
            "hdgdovmt-bmvyz-xjinphzm-bmvyz-zbb-pnzm-oznodib-109[zbmdn]\n" +
            "pejji-myvybpev-pvygob-kmaescsdsyx-978[vqufp]\n" +
            "hdgdovmt-bmvyz-agjrzm-vivgtndn-369[vdgmn]\n" +
            "rkpqxyib-zelzlixqb-obxznrfpfqflk-835[bflqx]\n" +
            "aczupnetwp-awldetn-rcldd-lnbftdtetzy-483[tdeln]\n" +
            "rwcnawjcrxwju-bljenwpna-qdwc-anjlzdrbrcrxw-719[huwmx]\n" +
            "gbc-frperg-pnaql-znantrzrag-637[ragnp]\n" +
            "houngfgxjuay-xghhoz-jkvruesktz-306[nsazy]\n" +
            "ugjjgkanw-kusnwfywj-zmfl-mkwj-lwklafy-918[wjkfl]\n" +
            "ykhknbqh-nwxxep-bejwjyejc-992[sgbzr]\n" +
            "drxevkzt-nvrgfezqvu-treup-ivjvrity-269[vreti]\n" +
            "qfmcusbwq-gqojsbusf-vibh-ghcfous-272[zobty]\n" +
            "pbafhzre-tenqr-qlr-znantrzrag-481[ranze]\n" +
            "wpuvcdng-ecpfa-vgejpqnqia-154[paceg]\n" +
            "mhi-lxvkxm-unggr-etuhktmhkr-605[hkmgr]\n" +
            "bnqqnrhud-atmmx-sqzhmhmf-157[mhqna]\n" +
            "vetllbybxw-vahvhetmx-hixktmbhgl-891[hbltv]\n" +
            "dpssptjwf-gvaaz-ezf-fohjoffsjoh-597[fjosa]\n" +
            "molgbzqfib-pzxsbkdbo-erkq-qoxfkfkd-289[zcjum]\n" +
            "myxcewob-qbkno-cmkfoxqob-rexd-dbksxsxq-562[ysjzt]\n" +
            "vhglnfxk-zktwx-xzz-xgzbgxxkbgz-215[sgtyf]\n" +
            "jlidywncfy-xsy-xymcah-110[ycxad]\n" +
            "ktiaaqnqml-kivlg-wxmzibqwva-330[aiqkl]\n" +
            "etaqigpke-rncuvke-itcuu-fgukip-934[ueikc]\n" +
            "ksodcbwnsr-qvcqczohs-oqeiwgwhwcb-922[cwoqs]\n" +
            "raphhxuxts-gpqqxi-gtprfjxhxixdc-219[oytzu]\n" +
            "fab-eqodqf-dmnnuf-emxqe-872[zsbvi]\n" +
            "zlkprjbo-doxab-oxyyfq-rpbo-qbpqfkd-991[bopqd]\n" +
            "drxevkzt-vxx-tfekrzedvek-217[lkyzs]\n" +
            "qekrixmg-aietsrmdih-hci-qerekiqirx-412[tasnf]\n" +
            "nsyjwsfyntsfq-idj-hzxytrjw-xjwanhj-463[jnswy]\n" +
            "cxy-bnlanc-ljwmh-lxjcrwp-vjatncrwp-173[cjlnw]\n" +
            "apuut-nxvqzibzm-cpio-nzmqdxzn-889[cvjhy]\n" +
            "wsvsdkbi-qbkno-lexxi-wkbuodsxq-172[bksxd]\n" +
            "clotzlnetgp-awldetn-rcldd-ecltytyr-327[ltcde]\n" +
            "qspkfdujmf-dboez-dpbujoh-ufdiopmphz-571[dpfou]\n" +
            "willimcpy-wuhxs-wiuncha-lywycpcha-266[gzrbt]\n" +
            "jvsvymbs-ibuuf-ylhjxbpzpapvu-227[dbpkh]\n" +
            "bnmrtldq-fqzcd-bgnbnkzsd-rsnqzfd-599[dnbqz]\n" +
            "tbxmlkfwba-ciltbo-abmilvjbkq-549[blaik]\n" +
            "ujoon-hrpktcvtg-wjci-sthxvc-115[cthjo]\n" +
            "cybyjqho-whqtu-sxesebqju-ijehqwu-998[qehju]\n" +
            "qcbgiasf-ufors-awzwhofm-ufors-pibbm-qighcasf-gsfjwqs-948[rfsbq]\n" +
            "mvydjvxodqz-kgvnodx-bmvnn-mzvxlpdndodji-369[dvnmo]\n" +
            "iutyaskx-mxgjk-ckgvutofkj-yigbktmkx-natz-xkikobotm-852[ktxgi]\n" +
            "ohmnuvfy-yaa-lywycpcha-994[xwflv]\n" +
            "xjinphzm-bmvyz-agjrzm-ncdkkdib-499[mzbdi]\n" +
            "luxciuwncpy-dyffsvyuh-omyl-nymncha-630[fcgdy]\n" +
            "rwcnawjcrxwju-ajkkrc-uxprbcrlb-511[rcjwa]\n" +
            "oqnidbshkd-eknvdq-qdzbpthrhshnm-703[upwnl]\n" +
            "vhglnfxk-zktwx-cxeeruxtg-lxkobvxl-761[xkleg]\n" +
            "dmybmsuzs-yuxufmdk-sdmpq-rxaiqd-efadmsq-612[nlayh]\n" +
            "fbebmtkr-zktwx-ynssr-vahvhetmx-inkvatlbgz-761[drsyx]\n" +
            "xmrrq-tskcwl-klgjsyw-164[qbztu]\n" +
            "tcrjjzwzvu-upv-uvgcfpdvek-945[nheck]\n" +
            "vkrhzxgbv-ietlmbv-zktll-hixktmbhgl-839[lbhkt]\n" +
            "drxevkzt-irsszk-crsfirkfip-971[riksf]\n" +
            "tpspahyf-nyhkl-zjhclunly-obua-zavyhnl-201[hlyan]\n" +
            "enzcntvat-enqvbnpgvir-enoovg-freivprf-403[nverf]\n" +
            "iehepwnu-cnwza-ydkykhwpa-qoan-paopejc-888[apenw]\n" +
            "laffe-vrgyzoi-mxgyy-xkgiwaoyozout-878[fzrnp]\n" +
            "luxciuwncpy-wuhxs-wiuncha-xyjfisgyhn-812[uchin]\n" +
            "houngfgxjuay-yigbktmkx-natz-ktmotkkxotm-358[ktgmo]\n" +
            "qzlozfhmf-bgnbnkzsd-qdzbpthrhshnm-911[hzbnd]\n" +
            "mvkccspson-tovvilokx-zebmrkcsxq-432[ckosv]\n" +
            "pybgmyargtc-zyqicr-ylyjwqgq-652[gyezv]\n" +
            "gvaaz-tdbwfohfs-ivou-mbcpsbupsz-129[ojwuz]\n" +
            "ftzgxmbv-xzz-wxitkmfxgm-163[xmzfg]\n" +
            "ujqgywfau-wyy-ksdwk-970[wykua]\n" +
            "nwlddtqtpo-ojp-dezclrp-119[dplot]\n" +
            "rgllk-qss-ymdwqfuzs-274[lzpkf]\n" +
            "ugfkmewj-yjsvw-xdgowj-jwuwanafy-918[wjafg]\n" +
            "lxaaxbren-bljenwpna-qdwc-ydalqjbrwp-537[amnfi]\n" +
            "jfifqxov-doxab-mixpqfz-doxpp-obxznrfpfqflk-263[fxopq]\n" +
            "ygcrqpkbgf-hnqygt-vtckpkpi-310[gkpcq]\n" +
            "nzcczdtgp-nsznzwlep-opgpwzaxpye-353[kfyqu]\n" +
            "iutyaskx-mxgjk-laffe-xghhoz-zxgototm-436[csnwy]\n" +
            "jyfvnlupj-msvdly-yljlpcpun-617[xjyin]\n" +
            "crwwv-zxkav-zlxqfkd-abmilvjbkq-809[kvabl]\n" +
            "dkqjcbctfqwu-uecxgpigt-jwpv-tgceswkukvkqp-388[ckgpq]\n" +
            "hjgbwuladw-tskcwl-vwkayf-632[waklb]\n" +
            "gifavtkzcv-tyftfcrkv-jrcvj-607[vcftj]\n" +
            "vcibutulxiom-lugjuacha-mwupyhayl-bohn-xymcah-110[zaukx]\n" +
            "eadalsjq-yjsvw-tmffq-jwsuimakalagf-450[afjsl]\n" +
            "pejji-mrymyvkdo-ecob-docdsxq-172[docej]\n" +
            "tinnm-dzoghwq-ufogg-hfowbwbu-844[gowbf]\n" +
            "szfyrqriuflj-upv-tljkfdvi-jvimztv-347[vfijl]\n" +
            "emixwvqhml-xtiabqk-oziaa-abwziom-980[mzdxn]\n" +
            "cvabijtm-kwvacumz-ozilm-lgm-mvoqvmmzqvo-382[mvoza]\n" +
            "forwcoqhwjs-foppwh-gsfjwqsg-454[wfosg]\n" +
            "lejkrscv-jtrmvexvi-ylek-glityrjzex-503[ejlrv]\n" +
            "bjfutsneji-jll-qtlnxynhx-333[jlntx]\n" +
            "xgvnndadzy-wvnfzo-nzmqdxzn-915[nzdvx]\n" +
            "iuxxuyobk-yigbktmkx-natz-jkvruesktz-358[hkglx]\n" +
            "yhwooebeaz-ywjzu-pnwejejc-602[ejwoy]\n" +
            "gpsxdprixkt-rpcsn-hpath-115[phrst]\n" +
            "qzoggwtwsr-xszzmpsob-fsoqeiwgwhwcb-766[qokpy]\n" +
            "enzcntvat-pubpbyngr-pbagnvazrag-611[abzti]\n" +
            "tmrszakd-dff-kzanqzsnqx-781[zadfk]\n" +
            "gspsvjyp-tpewxmg-kveww-gywxsqiv-wivzmgi-880[wgvip]\n" +
            "htwwtxnaj-xhfajsljw-mzsy-jslnsjjwnsl-151[jswln]\n" +
            "mixpqfz-doxpp-absbilmjbkq-705[bjkty]\n" +
            "ykhknbqh-oywrajcan-dqjp-opknwca-732[ulyzv]\n" +
            "gspsvjyp-gerhc-wivzmgiw-932[gipsv]\n" +
            "yaxsnlcrun-yujbcrl-pajbb-uxprbcrlb-407[brclu]\n" +
            "kpvgtpcvkqpcn-lgnnadgcp-qrgtcvkqpu-128[nuklw]\n" +
            "votubcmf-sbccju-fohjoffsjoh-571[focjb]\n" +
            "zixppfcfba-zxkav-obzbfsfkd-549[fbzak]\n" +
            "gbc-frperg-rtt-grpuabybtl-221[rbgtp]\n" +
            "fhezusjybu-fbqijys-whqii-mehaixef-842[fyqxt]\n" +
            "zlilocri-zloolpfsb-gbiivybxk-jxkxdbjbkq-497[bilko]\n" +
            "kgjgrypw-epybc-zsllw-rcaflmjmew-964[lwceg]\n" +
            "amjmpdsj-djmucp-qrmpyec-314[mjpcd]\n" +
            "fruurvlyh-iorzhu-pdunhwlqj-413[ndqft]\n" +
            "jchipqat-tvv-rjhidbtg-htgkxrt-193[mfnvi]\n" +
            "vcibutulxiom-mwupyhayl-bohn-uwkocmcncih-968[cuhim]\n" +
            "avw-zljyla-jovjvshal-zlycpjlz-721[ljavz]\n" +
            "oazegyqd-sdmpq-nmewqf-pqbxakyqzf-352[qadef]\n" +
            "rdggdhxkt-qjccn-prfjxhxixdc-297[xcdgh]\n" +
            "hwdtljsnh-gzssd-knsfshnsl-281[shndl]\n" +
            "tipfxvezt-nvrgfezqvu-treup-tfrkzex-rthlzjzkzfe-451[mezst]\n" +
            "uiovmbqk-zijjqb-camz-bmabqvo-356[znrpy]\n" +
            "zilqwikbqdm-kivlg-kwibqvo-xczkpiaqvo-876[ehjwb]\n" +
            "shoewudys-rkddo-husuylydw-166[dsuyh]\n" +
            "ytu-xjhwjy-jll-xfqjx-125[znmyk]\n" +
            "zlilocri-ciltbo-cfkxkzfkd-107[ciklf]\n" +
            "kfg-jvtivk-wcfnvi-kirzezex-581[xbemd]\n" +
            "qvbmzvibqwvit-ntwemz-zmamizkp-356[mzivb]\n" +
            "ajyqqgdgcb-pyzzgr-bcqgel-470[gqbcy]\n" +
            "zgmfyxypbmsq-zyqicr-nspafyqgle-340[maipd]\n" +
            "pxtihgbsxw-wrx-etuhktmhkr-709[htxkr]\n" +
            "lxwbdvna-pajmn-ajvyjprwp-ljwmh-lxjcrwp-jwjuhbrb-303[jwpab]\n" +
            "tyepcyletzylw-clmmte-pyrtyppctyr-613[ytpce]\n" +
            "gspsvjyp-gerhc-gsexmrk-vigimzmrk-360[gmrse]\n" +
            "tmrszakd-okzrshb-fqzrr-rghoohmf-599[rhozf]\n" +
            "uwtojhynqj-hfsid-btwpxmtu-983[igebf]\n" +
            "tpspahyf-nyhkl-wshzapj-nyhzz-zavyhnl-461[rhtdy]\n" +
            "atyzghrk-xghhoz-iayzuskx-ykxboik-774[khxyz]\n" +
            "nwlddtqtpo-qwzhpc-fdpc-epdetyr-847[hsouv]\n" +
            "bpvctixr-snt-pcpanhxh-167[pchnt]\n" +
            "bxaxipgn-vgpst-gpsxdprixkt-rwdrdapit-rdcipxcbtci-609[pixdr]\n" +
            "jfifqxov-doxab-yrkkv-pxibp-991[ysvte]\n" +
            "hqcfqwydw-rkddo-tuiywd-504[nlasz]\n" +
            "ibghopzs-forwcoqhwjs-pibbm-twbobqwbu-792[csjyt]\n" +
            "yrwxefpi-fmsledevhsyw-fewoix-erepcwmw-672[ewfim]\n" +
            "jrncbavmrq-cebwrpgvyr-qlr-pbagnvazrag-533[rabgv]\n" +
            "iwcjapey-fahhuxawj-naoawnyd-706[awhjn]\n" +
            "houngfgxjuay-jek-ktmotkkxotm-358[kotgj]\n" +
            "dsxxw-aylbw-amyrgle-bcnyprkclr-496[lryab]\n" +
            "szfyrqriuflj-wcfnvi-glityrjzex-555[wpmhn]\n" +
            "eqnqthwn-lgnnadgcp-gpikpggtkpi-544[cndbf]\n" +
            "fnjyxwrinm-lqxlxujcn-uxprbcrlb-433[xlnrb]\n" +
            "bwx-amkzmb-kwtwznct-kivlg-kwibqvo-bziqvqvo-772[yfklb]\n" +
            "emixwvqhml-kivlg-zmkmqdqvo-824[mqvik]\n" +
            "dpmpsgvm-dboez-qvsdibtjoh-181[iqunm]\n" +
            "wlqqp-avccpsvre-tljkfdvi-jvimztv-451[neohc]\n" +
            "ikhcxvmbex-ktuubm-mxvaghehzr-813[hmxbe]\n" +
            "yhkpvhjapcl-ihzrla-wbyjohzpun-409[hpajl]\n" +
            "ucynmlgxcb-kgjgrypw-epybc-zsllw-bctcjmnkclr-470[clbgy]\n" +
            "gbc-frperg-pynffvsvrq-rtt-fgbentr-533[rfgtb]\n" +
            "odiih-mhn-mnyuxhvnwc-693[yikst]\n" +
            "pelbtravp-fpniratre-uhag-qrcnegzrag-897[eflsv]\n" +
            "ojk-nzxmzo-mvwwdo-kpmxcvndib-811[qrbnz]\n" +
            "hafgnoyr-qlr-erprvivat-949[ravef]\n" +
            "nij-mywlyn-zfiqyl-lyuwkocmcncih-968[orsct]\n" +
            "udpsdjlqj-udeelw-vdohv-179[dejlu]\n" +
            "houngfgxjuay-hatte-xkgiwaoyozout-358[qeuts]\n" +
            "zsxyfgqj-hfsid-htfynsl-uzwhmfxnsl-931[mcznp]\n" +
            "ujqgywfau-hdsklau-yjskk-jwuwanafy-918[aujkw]\n" +
            "buzahisl-kfl-thyrlapun-123[lahub]\n" +
            "jshzzpmplk-ihzrla-klzpnu-591[zlphk]\n" +
            "qmpmxevc-kvehi-fmsledevhsyw-hci-gywxsqiv-wivzmgi-984[ivemh]\n" +
            "apwmeclga-djmucp-qfgnngle-262[vbozj]\n" +
            "joufsobujpobm-ezf-mphjtujdt-545[enrth]\n" +
            "wlsiayhcw-wbiwifuny-ijyluncihm-136[iwych]\n" +
            "vehmsegxmzi-wgezirkiv-lyrx-vieguymwmxmsr-672[meigr]\n" +
            "forwcoqhwjs-pogysh-kcfygvcd-870[cofgh]\n" +
            "ryexqpqhteki-sqdto-seqjydw-bqrehqjeho-634[qehdj]\n" +
            "buzahisl-jvyyvzpcl-qlssfilhu-vwlyhapvuz-591[lvhsu]\n" +
            "vxupkizork-jek-zkinturume-852[tmzlr]\n" +
            "xmrrq-vqw-mkwj-lwklafy-762[wklmq]\n" +
            "irdgrxzex-tyftfcrkv-uvgrikdvek-555[rkvde]\n" +
            "fab-eqodqf-bxmefuo-sdmee-fdmuzuzs-742[uzdoy]\n" +
            "nij-mywlyn-vohhs-ijyluncihm-214[hwyfz]\n" +
            "lgh-kwujwl-tmffq-esfsywewfl-190[fwles]\n" +
            "pbeebfvir-pynffvsvrq-rtt-jbexfubc-611[izbpo]\n" +
            "dkqjcbctfqwu-uecxgpigt-jwpv-vtckpkpi-518[cpktg]\n" +
            "lejkrscv-srjbvk-jyzggzex-399[jegkr]\n" +
            "cqwdujys-rkddo-huqsgkyiyjyed-322[dyjkq]\n" +
            "apuut-xviyt-xjvodib-mznzvmxc-499[vximt]\n" +
            "aietsrmdih-fyrrc-pskmwxmgw-776[gltyk]\n" +
            "aczupnetwp-awldetn-rcldd-cpdplcns-743[cdpln]\n" +
            "kpvgtpcvkqpcn-hnqygt-ncdqtcvqta-570[cqtnp]\n" +
            "tcrjjzwzvu-sleep-uvmvcfgdvek-789[vecju]\n" +
            "crwwv-zxkav-zlxqfkd-cfkxkzfkd-653[kfxzc]\n" +
            "kgjgrypw-epybc-aylbw-cleglccpgle-366[cglep]\n" +
            "sebehvkb-rkddo-udwyduuhydw-244[yxpql]\n" +
            "wpuvcdng-ecpfa-eqcvkpi-ceswkukvkqp-648[ckpev]\n" +
            "qcbgiasf-ufors-qvcqczohs-igsf-hsghwbu-818[qvnjb]\n" +
            "sxdobxkdsyxkv-mkxni-mykdsxq-wkxkqowoxd-796[upngm]\n" +
            "cxy-bnlanc-lqxlxujcn-vjatncrwp-355[cnlxa]\n" +
            "ohmnuvfy-jfumncw-alumm-womnigyl-mylpcwy-240[biqdh]\n" +
            "tyepcyletzylw-awldetn-rcldd-lylwjdtd-275[tsenk]\n" +
            "vrurcjah-pajmn-yujbcrl-pajbb-fxatbqxy-511[drzlg]\n" +
            "ikhcxvmbex-yehpxk-hixktmbhgl-397[hxkbe]\n" +
            "kzgwomvqk-jcvvg-wxmzibqwva-824[mwzye]\n" +
            "tfejldvi-xiruv-treup-vexzevvizex-295[evixr]\n" +
            "frqvxphu-judgh-vfdyhqjhu-kxqw-pdqdjhphqw-361[hqdjp]\n" +
            "yflexwxoalrp-mixpqfz-doxpp-bkdfkbbofkd-497[fpxbd]\n" +
            "emixwvqhml-kivlg-bziqvqvo-382[nwsap]\n" +
            "mvydjvxodqz-ytz-xpnojhzm-nzmqdxz-863[mitys]\n" +
            "hcd-gsqfsh-gqojsbusf-vibh-ghcfous-844[shfgb]\n" +
            "wfruflnsl-hfsid-htfynsl-hzxytrjw-xjwanhj-775[fhjln]\n" +
            "tbxmlkfwba-zelzlixqb-qoxfkfkd-393[bfklx]\n" +
            "xjmmjndqz-wpiit-adivixdib-395[idjmx]\n" +
            "wyvqljapsl-jovjvshal-thuhnltlua-201[lahjv]\n" +
            "pkl-oaynap-lhwopey-cnwoo-zalhkuiajp-446[aoplh]\n" +
            "ugfkmewj-yjsvw-usfvq-kzahhafy-164[fahjk]\n" +
            "sebehvkb-hqcfqwydw-sqdto-iqbui-738[qbdeh]\n" +
            "mfklstdw-wyy-suimakalagf-814[afklm]\n" +
            "htsxzrjw-lwfij-xhfajsljw-mzsy-uzwhmfxnsl-983[jswfh]\n" +
            "chnylhuncihuf-vohhs-uhufsmcm-994[hucfm]\n" +
            "otzkxtgzoutgr-kmm-iayzuskx-ykxboik-696[kotxz]\n" +
            "htwwtxnaj-wfggny-ijufwyrjsy-307[mlrnu]\n" +
            "wlsiayhcw-wuhxs-lywycpcha-916[wchya]\n" +
            "htqtwkzq-gzssd-fhvznxnynts-931[nstzh]\n" +
            "tcfkqcevkxg-ecpfa-eqcvkpi-eqpvckpogpv-570[zvtny]\n" +
            "crwwv-yxphbq-obpbxoze-809[bopwx]\n" +
            "xjgjmapg-wpiit-hvmfzodib-681[wtrmn]\n" +
            "ksodcbwnsr-ibghopzs-qobrm-qcohwbu-gsfjwqsg-324[sbogq]\n" +
            "jchipqat-hrpktcvtg-wjci-bpcpvtbtci-323[ctpib]\n" +
            "lujbbrornm-npp-mnyjacvnwc-433[nbcjm]\n" +
            "xgjougizobk-lruckx-zkinturume-488[ukgio]\n" +
            "lxaaxbren-mhn-vjwjpnvnwc-459[najvw]\n" +
            "kyelcrga-bwc-pcacgtgle-834[cgael]\n" +
            "sgmtkzoi-vrgyzoi-mxgyy-sgxqkzotm-618[gmoyz]\n" +
            "surmhfwloh-fdqgb-frdwlqj-uhvhdufk-777[fhdul]\n" +
            "vxupkizork-hatte-ynovvotm-670[otvka]\n" +
            "amlqskcp-epybc-afmamjyrc-amlryglkclr-704[aclmr]\n" +
            "yaxsnlcrun-lqxlxujcn-jlzdrbrcrxw-719[pyomt]\n" +
            "ohmnuvfy-vohhs-yhachyylcha-578[xkpiy]\n" +
            "wdjcvuvmyjpn-ytz-jkzmvodjin-551[cairj]\n" +
            "aflwjfslagfsd-tskcwl-vwhsjlewfl-892[lfswa]\n" +
            "hcd-gsqfsh-aoubshwq-gqojsbusf-vibh-qighcasf-gsfjwqs-376[shqfg]\n" +
            "gbc-frperg-pnaql-pbagnvazrag-767[agprb]\n" +
            "emixwvqhml-jcvvg-wxmzibqwva-876[vmwiq]\n" +
            "nuatmlmdpage-omzpk-oamfuzs-bgdotmeuzs-716[maouz]\n" +
            "glrcplyrgmlyj-djmucp-mncpyrgmlq-340[acoxz]\n" +
            "qczcftiz-dzoghwq-ufogg-hfowbwbu-818[fgowz]\n" +
            "apuut-nxvqzibzm-cpio-yzkvmohzio-629[ziomp]\n" +
            "gpsxdprixkt-bpvctixr-gpqqxi-prfjxhxixdc-323[jxkig]\n" +
            "rmn-qcapcr-njyqrga-epyqq-bctcjmnkclr-704[cqrna]\n" +
            "wlsiayhcw-zfiqyl-jolwbumcha-708[swavu]\n" +
            "njmjubsz-hsbef-cvooz-usbjojoh-675[jobsh]\n" +
            "kpvgtpcvkqpcn-dcumgv-ceswkukvkqp-492[kcpvg]\n" +
            "egdytrixat-raphhxuxts-qphzti-steadnbtci-505[tahix]\n" +
            "qcbgiasf-ufors-xszzmpsob-rsdzcmasbh-402[ijlzr]\n" +
            "kfg-jvtivk-jtrmvexvi-ylek-uvjzxe-217[vejki]\n" +
            "vetllbybxw-xzz-hixktmbhgl-917[blxht]\n" +
            "htwwtxnaj-idj-fsfqdxnx-931[xdfjn]\n" +
            "muqfedyput-fbqijys-whqii-skijecuh-iuhlysu-842[gsjin]\n" +
            "dmybmsuzs-pkq-dqeqmdot-144[dmqsb]\n" +
            "nvrgfezqvu-wcfnvi-jvimztvj-815[vfijn]\n" +
            "aoubshwq-qvcqczohs-gsfjwqsg-272[qscgh]\n" +
            "bknsykmdsfo-mkxni-nozvyiwoxd-510[knodi]\n" +
            "bnmrtldq-fqzcd-dff-ehmzmbhmf-157[fmdbh]\n" +
            "qczcftiz-pibbm-rsjszcdasbh-142[eafby]\n" +
            "vagreangvbany-pnaql-nanylfvf-195[anvfg]\n" +
            "oxjmxdfkd-zlilocri-yxphbq-abmilvjbkq-497[zvsko]\n" +
            "lejkrscv-tyftfcrkv-uvjzxe-191[vcefj]\n" +
            "qekrixmg-gerhc-gsexmrk-hitpscqirx-698[regix]\n" +
            "xgsvgmotm-hgyqkz-xkgiwaoyozout-462[gokmt]\n" +
            "dpssptjwf-dszphfojd-ezf-tupsbhf-961[zyots]\n" +
            "jyfvnlupj-lnn-bzly-alzapun-123[uvkxg]\n" +
            "jsehsyafy-hdsklau-yjskk-umklgewj-kwjnauw-840[kjsau]\n" +
            "vdzonmhydc-bzmcx-bnzshmf-otqbgzrhmf-937[qxzpu]\n" +
            "qmpmxevc-kvehi-jpsaiv-stivexmsrw-126[xdsef]\n" +
            "ymszqfuo-vqxxknqmz-ymdwqfuzs-300[qmzfs]\n" +
            "qfkkj-nsznzwlep-zapcletzyd-561[nxzqv]\n" +
            "krxqjijamxdb-snuuhknjw-mnyjacvnwc-771[fgnhu]\n" +
            "tfiifjzmv-tyftfcrkv-jvimztvj-529[cgbts]\n" +
            "wfruflnsl-wfggny-qfgtwfytwd-957[fwgln]\n" +
            "kdijqrbu-tou-vydqdsydw-218[rkftc]\n" +
            "cebwrpgvyr-sybjre-freivprf-871[rebfp]\n" +
            "fmsledevhsyw-gpewwmjmih-glsgspexi-ywiv-xiwxmrk-958[weims]\n" +
            "yhtwhnpun-wshzapj-nyhzz-mpuhujpun-981[hnpuz]\n" +
            "muqfedyput-fbqijys-whqii-tufbeocudj-166[ufiqb]\n" +
            "vkrhzxgbv-utldxm-nlxk-mxlmbgz-137[xlmbg]\n" +
            "jsvagsulanw-ugjjgkanw-wyy-vwhsjlewfl-398[wjagl]\n" +
            "xtwtelcj-rclop-nsznzwlep-opawzjxpye-795[pelwz]\n" +
            "wyvqljapsl-yhiipa-zopwwpun-383[smzin]\n" +
            "ejpanjwpekjwh-xekdwvwnzkqo-oywrajcan-dqjp-oanreyao-264[ajwen]\n" +
            "ugfkmewj-yjsvw-bwddqtwsf-ogjckzgh-736[wgjdf]\n" +
            "yflexwxoalrp-bdd-qoxfkfkd-965[dfxkl]\n" +
            "kdijqrbu-sbqiiyvyut-fbqijys-whqii-bewyijysi-660[iybqj]\n" +
            "nzcczdtgp-mldvpe-hzcvdsza-743[tsrmc]\n" +
            "eza-dpncpe-upwwjmply-dpcgtnpd-457[pdcen]\n" +
            "iruzfrtkzmv-tyftfcrkv-dribvkzex-789[zcudt]\n" +
            "tfejldvi-xiruv-srjbvk-crsfirkfip-451[irfvj]\n" +
            "otzkxtgzoutgr-igtje-iugzotm-ykxboiky-280[tgoik]\n" +
            "ohmnuvfy-luvvcn-ijyluncihm-682[nuvch]\n" +
            "eadalsjq-yjsvw-usfvq-ugslafy-jwuwanafy-450[asfju]\n" +
            "fkqbokxqflkxi-yxphbq-cfkxkzfkd-653[kfxqb]\n" +
            "owshgfarwv-uzgugdslw-vwhsjlewfl-372[wglsf]\n" +
            "ygcrqpkbgf-dcumgv-octmgvkpi-414[hzick]\n" +
            "ujoon-ytaanqtpc-pcpanhxh-869[anpch]\n" +
            "wlsiayhcw-vohhs-lymyulwb-682[areml]\n" +
            "dlhwvupglk-zjhclunly-obua-zopwwpun-617[lupwh]\n" +
            "iuxxuyobk-inuiurgzk-jkyomt-228[uikox]\n" +
            "zuv-ykixkz-hgyqkz-ynovvotm-332[kvyzo]\n" +
            "ujqgywfau-bwddqtwsf-kwjnauwk-190[wuadf]\n" +
            "frqvxphu-judgh-udeelw-orjlvwlfv-361[luvde]\n" +
            "vqr-ugetgv-dwppa-octmgvkpi-102[gpvta]\n" +
            "iuxxuyobk-vrgyzoi-mxgyy-iayzuskx-ykxboik-410[yxiko]\n" +
            "vhglnfxk-zktwx-ietlmbv-zktll-ltexl-995[ltkxe]\n" +
            "ktiaaqnqml-kivlg-kwvbiqvumvb-746[vikqa]\n" +
            "zlkprjbo-doxab-pzxsbkdbo-erkq-rpbo-qbpqfkd-601[bkopd]\n" +
            "rdchjbtg-vgpst-rpcsn-rdpixcv-hidgpvt-973[pcdgr]\n" +
            "ixccb-udeelw-ghvljq-803[celbd]\n" +
            "uzfqdzmfuazmx-vqxxknqmz-fqotzaxask-872[uskol]\n" +
            "qzlozfhmf-atmmx-cdoknxldms-833[mdflo]\n" +
            "bpvctixr-ytaanqtpc-gtprfjxhxixdc-505[mainf]\n" +
            "kgjgrypw-epybc-aylbw-bcnyprkclr-678[ybcpr]\n" +
            "hqfxxnknji-hwdtljsnh-xhfajsljw-mzsy-fhvznxnynts-645[nhjsx]\n" +
            "ymszqfuo-nmewqf-oazfmuzyqzf-352[fzmqo]\n" +
            "yhwooebeaz-lhwopey-cnwoo-wjwhuoeo-862[owehy]\n" +
            "muqfedyput-fbqijys-whqii-mehaixef-374[iefqh]\n" +
            "xqvwdeoh-vfdyhqjhu-kxqw-vdohv-621[hvdqo]\n" +
            "pybgmyargtc-qaytclecp-fslr-rcaflmjmew-990[cytzv]\n" +
            "fnjyxwrinm-bljenwpna-qdwc-cajrwrwp-849[wnjra]\n" +
            "fab-eqodqf-ymszqfuo-otaoaxmfq-pqbmdfyqzf-222[fqoam]\n" +
            "drxevkzt-tyftfcrkv-kvtyefcfxp-685[ftkvc]\n" +
            "pyknyegle-afmamjyrc-mncpyrgmlq-340[myace]\n" +
            "ykhknbqh-ywjzu-ykwpejc-iwngapejc-914[ixmoc]\n" +
            "ydjuhdqjyedqb-isqludwuh-xkdj-ijehqwu-530[cryum]\n" +
            "dpssptjwf-kfmmzcfbo-mphjtujdt-207[fjmpt]\n" +
            "jvuzbtly-nyhkl-yhiipa-zavyhnl-123[yhlai]\n" +
            "eqttqukxg-dwppa-fgrnqaogpv-908[pntsf]\n" +
            "tcorcikpi-hnqygt-fgrctvogpv-232[cgtio]\n" +
            "zotts-wuhxs-qilembij-422[fazws]\n" +
            "pelbtravp-sybjre-hfre-grfgvat-273[xwnyt]\n" +
            "pbafhzre-tenqr-qlr-erfrnepu-117[refnp]\n" +
            "luxciuwncpy-yaa-qilembij-734[iaclu]\n" +
            "fbebmtkr-zktwx-wrx-kxtvjnblbmbhg-943[estfp]\n" +
            "rflsjynh-hmthtqfyj-uzwhmfxnsl-827[hfjlm]\n" +
            "ktwbhtvmbox-vahvhetmx-vhgmtbgfxgm-709[hmtvb]\n" +
            "fodvvlilhg-mhoobehdq-vwrudjh-517[hdovl]\n" +
            "lsyrkjkbnyec-lexxi-nozkbdwoxd-874[kxbde]\n" +
            "njmjubsz-hsbef-gmpxfs-eftjho-597[fjsbe]\n" +
            "qyujihctyx-luvvcn-fiacmncwm-604[cimnu]\n" +
            "udskkaxawv-wyy-umklgewj-kwjnauw-190[wkauj]\n" +
            "sebehvkb-zubboruqd-husuylydw-192[budeh]\n" +
            "nzwzcqfw-upwwjmply-dpcgtnpd-795[pwcdn]\n" +
            "zloolpfsb-bdd-qoxfkfkd-679[dfobk]\n" +
            "fhezusjybu-rkddo-cqdqwucudj-816[enzjf]\n" +
            "irgyyolokj-inuiurgzk-jkvruesktz-202[kirug]\n" +
            "bnqqnrhud-hmsdqmzshnmzk-atmmx-btrsnldq-rdquhbd-599[dmqhn]\n" +
            "avw-zljyla-jhukf-jvhapun-jbzavtly-zlycpjl-149[jlavy]\n" +
            "sno-rdbqds-bnqqnrhud-eknvdq-sqzhmhmf-729[qdnhs]\n" +
            "slqryzjc-aylbw-amyrgle-dglylagle-392[lyage]\n" +
            "hqfxxnknji-hfsid-fsfqdxnx-151[fxndh]\n" +
            "vehmsegxmzi-tpewxmg-kveww-pskmwxmgw-308[mwegx]\n" +
            "dzczkrip-xiruv-gcrjkzt-xirjj-ljvi-kvjkzex-633[jikrz]\n" +
            "mvhkvbdib-zbb-yzkvmohzio-915[bvzhi]\n" +
            "ejpanjwpekjwh-xwogap-zarahkliajp-316[ajpwe]\n" +
            "dwbcjkun-odiih-mhn-jlzdrbrcrxw-719[drbch]\n" +
            "gifavtkzcv-sleep-jkfirxv-633[vefik]\n" +
            "oxmeeuruqp-dmpuamofuhq-vqxxknqmz-oazfmuzyqzf-898[mquzf]\n" +
            "myvybpev-mkxni-mykdsxq-nocsqx-432[mxykn]\n" +
            "aczupnetwp-awldetn-rcldd-cpnptgtyr-431[ptcdn]\n" +
            "fab-eqodqf-dmnnuf-geqd-fqefuzs-222[fqden]\n" +
            "encuukhkgf-fag-ucngu-440[ugcfk]\n" +
            "cvabijtm-moo-twoqabqka-980[aobmq]\n" +
            "sawlkjevaz-lnkfaypeha-zua-opknwca-888[hlzus]\n" +
            "wfintfhynaj-ojqqdgjfs-yjhmstqtld-281[ibnmv]\n" +
            "kmjezxodgz-nxvqzibzm-cpio-gjbdnodxn-421[zdnox]\n" +
            "dfcxsqhwzs-qobrm-qcohwbu-qcbhowbasbh-402[bhqco]\n" +
            "joufsobujpobm-kfmmzcfbo-vtfs-uftujoh-441[orwsq]\n" +
            "nvrgfezqvu-treup-rthlzjzkzfe-607[zerft]\n" +
            "kfg-jvtivk-sleep-uvjzxe-555[evjkf]\n" +
            "jfifqxov-doxab-oxjmxdfkd-avb-zlkqxfkjbkq-627[xfkbd]\n" +
            "hcd-gsqfsh-qobrm-qcohwbu-rsgwub-350[bhqsc]\n" +
            "xst-wigvix-hci-hiwmkr-464[ihwxc]\n" +
            "yhwooebeaz-bhksan-odellejc-368[eoabh]\n" +
            "sehheiylu-zubboruqd-sedjqydcudj-452[mvars]\n" +
            "mybbycsfo-pvygob-cobfsmoc-302[amnsc]\n" +
            "ziuxioqvo-xtiabqk-oziaa-lmxtwgumvb-746[iaoxb]\n" +
            "gbc-frperg-sybjre-nanylfvf-377[xfjqz]\n" +
            "zekvierkzferc-dzczkrip-xiruv-tyftfcrkv-tljkfdvi-jvimztv-191[rhqtz]\n" +
            "lhkhszqx-fqzcd-idkkxadzm-cdrhfm-209[dhkzc]\n" +
            "mvkccspson-pvygob-bokmaescsdsyx-224[lkdtm]\n" +
            "vqr-ugetgv-dwppa-fgxgnqrogpv-336[nmitq]\n" +
            "nzwzcqfw-clmmte-pyrtyppctyr-717[lvyuh]\n" +
            "wsvsdkbi-qbkno-gokzyxsjon-lkcuod-bocokbmr-822[okbsc]\n" +
            "pyknyegle-djmucp-umpiqfmn-158[mpenu]\n" +
            "iqmbazulqp-rxaiqd-efadmsq-170[qadim]\n" +
            "irdgrxzex-sleep-dribvkzex-789[erxdi]\n" +
            "cxy-bnlanc-lxwbdvna-pajmn-yujbcrl-pajbb-orwjwlrwp-953[bajln]\n" +
            "wdjcvuvmyjpn-wpiit-yzqzgjkhzio-317[mtbqs]\n" +
            "rdchjbtg-vgpst-hrpktcvtg-wjci-hpath-661[zypto]\n" +
            "bwx-amkzmb-kivlg-kwibqvo-kwvbiqvumvb-980[htfdv]\n" +
            "sedikcuh-whqtu-zubboruqd-cqhaujydw-348[udhqb]\n" +
            "mbggf-jhukf-jvhapun-ylhjxbpzpapvu-149[phjua]\n" +
            "lzfmdshb-oqnidbshkd-cxd-sqzhmhmf-989[dhmsb]\n" +
            "zbytomdsvo-lkcuod-bokmaescsdsyx-510[osdbc]\n" +
            "iuxxuyobk-kmm-aykx-zkyzotm-384[nqfaj]\n" +
            "ejpanjwpekjwh-xwogap-odellejc-654[ejpwa]\n" +
            "dwbcjkun-yujbcrl-pajbb-anjlzdrbrcrxw-589[bjrca]\n" +
            "diozmivodjivg-ezggtwzvi-hvivbzhzio-239[ivzgo]\n" +
            "vqr-ugetgv-ecpfa-eqcvkpi-hkpcpekpi-362[peckv]\n" +
            "sxdobxkdsyxkv-mybbycsfo-mkxni-cobfsmoc-926[fnpzo]\n" +
            "yflexwxoalrp-zxkav-xkxivpfp-913[xpafk]\n" +
            "frqvxphu-judgh-hjj-whfkqrorjb-569[hjrfq]\n" +
            "sbqiiyvyut-rkddo-tufbeocudj-218[dubio]\n" +
            "esyfwlau-tmffq-kzahhafy-112[sadtv]\n" +
            "pbeebfvir-pubpbyngr-genvavat-715[zjqiy]\n" +
            "rgllk-vqxxknqmz-etubbuzs-534[bklqu]\n" +
            "nchhg-bwx-amkzmb-lgm-nqvivkqvo-486[mvbgh]\n" +
            "ykhknbqh-xqjju-hkceopeyo-160[utmir]\n" +
            "tmrszakd-okzrshb-fqzrr-sqzhmhmf-937[rzhms]\n" +
            "zhdsrqlchg-fdqgb-frdwlqj-vwrudjh-127[dhqrf]\n" +
            "eadalsjq-yjsvw-bwddqtwsf-ugflsafewfl-164[fswad]\n" +
            "sbejpbdujwf-kfmmzcfbo-xpsltipq-883[bfpjm]\n" +
            "kfg-jvtivk-vxx-rthlzjzkzfe-919[kvzfj]\n" +
            "ltpedcxots-eaphixr-vgphh-gthtpgrw-349[hptge]\n" +
            "bxaxipgn-vgpst-jchipqat-rpcsn-rjhidbtg-htgkxrt-531[tgphi]\n" +
            "clotzlnetgp-ojp-lylwjdtd-743[cqzyk]\n" +
            "zlkprjbo-doxab-zelzlixqb-pxibp-315[blpxz]\n" +
            "etyyx-bzmcx-qdzbpthrhshnm-755[bksta]\n" +
            "eqnqthwn-ecpfa-ceswkukvkqp-336[ekqcn]\n" +
            "jvyyvzpcl-ihzrla-zlycpjlz-383[lzycj]\n" +
            "wbhsfbohwcboz-pwcvonofrcig-suu-cdsfohwcbg-896[cobwf]\n" +
            "nwzekwypera-bhksan-nayaerejc-186[aenkr]\n" +
            "tmrszakd-eknvdq-lzmzfdldms-989[dmzkl]\n" +
            "szfyrqriuflj-wcfnvi-rthlzjzkzfe-971[nhmbi]\n" +
            "yflexwxoalrp-pzxsbkdbo-erkq-pbosfzbp-653[wctun]\n" +
            "iehepwnu-cnwza-ywjzu-ykwpejc-zalwnpiajp-524[czsja]\n" +
            "vkrhzxgbv-vtgwr-vhtmbgz-mktbgbgz-553[kweyh]\n" +
            "vetllbybxw-vtgwr-vhtmbgz-mxvaghehzr-553[vzicb]\n" +
            "rgllk-qss-etubbuzs-430[sblue]\n" +
            "udglrdfwlyh-gbh-ghyhorsphqw-465[hgdlr]\n" +
            "krxqjijamxdb-kdwwh-vjatncrwp-823[kvmoi]\n" +
            "tpspahyf-nyhkl-kfl-aljouvsvnf-981[flahk]\n" +
            "bxaxipgn-vgpst-qphzti-rdcipxcbtci-635[ipctx]";

    private static Comparator<Map.Entry<Character, Integer>> valueComparator = new Comparator<Map.Entry<Character, Integer>>() {
        @Override
        public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
            Integer v1 = o1.getValue();
            Integer v2 = o2.getValue();
            int value = v2.compareTo(v1);
            if (value == 0) {
                Character c1 = o1.getKey();
                Character c2 = o2.getKey();
                value = c1.compareTo(c2);
            }
            return value;
        }
    };

    public static void main(String[] args) {
        int totalSectorIds = 0;

        String lines[] = INPUT.split("\\n");

        List<String> validLines = new ArrayList<>();

        for (String line : lines) {
            // 5 most common letters in list in alphabetical order
            String mostCommon = getMostCommonLetters(line);
            String checkSumValue = getCheckSum(line);

            if (checkSumValue.equals(mostCommon)) {
                int sectorId = getSectorId(line);
                totalSectorIds += sectorId;

                validLines.add(line);
            }
        }

        System.out.println("total = [" + totalSectorIds + "]");
        System.out.println("--------------------------------");
        System.out.println("------------ PART 2 ------------");
        System.out.println("--------------------------------");

        //
        for (String line : lines) {
            System.out.println(decrypt(line));
        }
    }

    private static String decrypt(String line) {
        int sId = getSectorId(line);
        int bracketLeftPos = line.indexOf("[");
        String prhase = line.substring(0, bracketLeftPos - 4);

        int numberOfRols = sId % 26;
        StringBuilder sb = new StringBuilder();
        for (char c : prhase.toCharArray()) {
            if (c == '-') {
                sb.append(" ");
            } else {
                int currentValue = c;
                int newValue = currentValue + numberOfRols;
                if (newValue > 122) {
                    newValue -= 26;
                }
                sb.append((char) newValue);
            }
        }

        return sb.toString() + "(" + sId + ")";
    }

    private static int getSectorId(String line) {
        return Integer.parseInt(line.replaceAll("\\D+",""));
    }

    private static String getCheckSum(String line) {
        int bracketLeftPos = line.indexOf("[");
        int bracketRightPos = line.indexOf("]");

        return line.substring(bracketLeftPos + 1, bracketRightPos);
    }

    private static String getMostCommonLetters(String line) {
        StringBuilder mostCommon = new StringBuilder();
        Map<Character, Integer> appearances = new HashMap<>();

        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                break;
            }

            if (c != '-') {
                if (appearances.containsKey(c)) {
                    appearances.put(c, appearances.get(c) + 1);
                } else {
                    appearances.put(c, 1);
                }
            }
        }

        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(appearances.entrySet());


        Collections.sort(entries, valueComparator);

        for (int i = 0; i < 5; i++) {
            mostCommon.append(entries.get(i).getKey());
        }

        return mostCommon.toString();
    }
}
