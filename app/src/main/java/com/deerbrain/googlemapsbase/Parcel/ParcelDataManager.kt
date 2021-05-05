package com.deerbrain.googlemapsbase.Parcel

import android.net.Uri
import androidx.core.net.toUri
import com.google.android.gms.maps.model.LatLng

class ParcelDataManager {
    val token = "toBeSharedLater" //dont share this
    val base =
        "https://reportallusa.com/api/parcels.php?client={token}&v=4&rpp=1&spatial_nearest=POINT({long}%20{lat})&sn_srid=4326"


    //I am not familiar with callbacks in kotlin so i will describe what happens here:
//this fetchParcelDat gets one input variable and the function then makes a URL request to get information.
//when the information is received the Build a ParcelDetailInfo and return that info
    public fun fetchParcelData(latLng: LatLng, callBack: (ArrayList<ParcelDetailInfo>) -> Unit) {
        val url = buildURL(latLng)

//        //Asycrounous call to buildURL location Returns "DATA"
//
//            // val parcelDetailData = JSON.Decoder.Decode(ReportAllDataStructure, from: DATA)  //This is how swift doees it
//
//            if (parcelDetailData != null) {
//                val found = parcelDetailData.results.first
//
//                val name = found.owner ?? noData
//                val address1 = found.mail_address1 ?? noData
//                val address3 = found.mail_address3 ?? noData
//                val fullAddress = "\(String(describing: address1))\n\(String(describing: address3))"
//                val polygon = found.geom_as_wkt ?? noData
//                val acresCalc = found.acreage_calc ?? noData
//                val acresDeed = found.acreage_deeded ?? noData
//                val shape = parseMultiPolygon(polygon)
//
//                val detailInfo = ParcelDetailInfo(ownerName = name,fullAddress = fullAddress,address1 = address1,address2 = address3,calcAcres = acresCalc,deedAcres = acresDeed,shapeFile = shape,shape2D = shape)
//
//                callback(ArrayList<detailInfo>)
//            }
//
//

    }


    private fun buildURL(location: LatLng): Uri {
        var urlString = base
        urlString = urlString.replace("{lat}", location.latitude.toString(), true)
        urlString = urlString.replace("{long}", location.longitude.toString(), true)
        val myUri = urlString.toUri()
        return myUri
    }

    private fun parseMultiPolygon(shapeStringFile: String): ArrayList<ArrayList<LatLng>> {
        // the info from the call will be a long string file. This will take that string and the split it out into an array of lat and longs.
        // attached are the code used in swift to provide a guideline of what is needed


        //       let pieces = shapeStringFile.trimmingCharacters(in: .alphanumerics).components(separatedBy: "),(")

//        return pieces.map { chunk -> [CLLocationCoordinate2D] in
//                let raw = chunk.trimmingCharacters(in: CharacterSet(charactersIn: "()"))
//            let components = raw.components(separatedBy: ",")
//            return components.compactMap { chunk in
//                    let dbls = chunk.components(separatedBy: .whitespacesAndNewlines).compactMap { Double($0) }
//                if dbls.count != 2 { return nil }
//                return CLLocationCoordinate2D(latitude: dbls[1], longitude: dbls[0])
//            }

    }

}


//Below is an example of a multipolygon returned from the call   this is the return in swift but give you an idea.
//MULTIPOLYGON(((-81.8224064138443 34.9583006897114,-81.8222246131439 34.9579837841347,-81.8220262641855 34.9576736955595,-81.8217265011485 34.9572154704146,-81.8216443359493 34.9570968543928,-81.8215556872985 34.9569814112252,-81.8214607678194 34.9568693891889,-81.8207708250561 34.9561494144481,-81.8189050228209 34.9540980339964,-81.8187346103107 34.9539112768573,-81.8185541171351 34.9537310697244,-81.8185640907447 34.952999099987,-81.8185655129357 34.952980214947,-81.8185680654003 34.9529613766186,-81.8185717240775 34.9529427014364,-81.8185765170567 34.9529241504578,-81.818582380893 34.9529058430784,-81.8185893508164 34.9528877998001,-81.8185973866597 34.9528700560383,-81.8186064623398 34.9528526317933,-81.8186165425976 34.9528356174328,-81.818627658284 34.9528190334869,-81.818639720978 34.952802906672,-81.8186527498963 34.9527872999591,-81.8186666861319 34.9527722353651,-81.8186814993187 34.9527577428333,-81.8201965506294 34.9514103336478,-81.820168988379 34.9513896332977,-81.8204024525171 34.9511702387549,-81.8207722283623 34.9508298941033,-81.8216678284072 34.9499995341476,-81.8225261610934 34.9492169440333,-81.8211119131829 34.9487201337171,-81.8197813634097 34.9482505089051,-81.8190089316912 34.9479753158976,-81.8191495242739 34.946601243131,-81.8198310540449 34.9465855268881,-81.8212156526321 34.9463275873491,-81.8213799447591 34.9459294832294,-81.8211406968742 34.9457208752429,-81.8209327481747 34.9455749722409,-81.820922267316 34.9444538209253,-81.8209265007584 34.9442907907578,-81.8209366846054 34.9439424342526,-81.8214332920011 34.9436094950407,-81.8214824006504 34.9435803005061,-81.8217379319714 34.9434039179675,-81.821948763664 34.9432618692851,-81.8219744151242 34.9432440900572,-81.8221419938899 34.9431279019635,-81.8221909952838 34.9430885663759,-81.8222568389569 34.9430315389376,-81.8223193844957 34.9429783810791,-81.8223808648035 34.9429180821944,-81.82241872899 34.9428661856941,-81.8224511088804 34.9427986590699,-81.8224745003979 34.9427333907151,-81.8224822193984 34.9426687767095,-81.8224836558768 34.9426102512577,-81.822468437783 34.942554850978,-81.8224471574962 34.9424934475434,-81.8224179499869 34.9424403383049,-81.8223698548151 34.9424005427735,-81.8223106281991 34.9423481791482,-81.8222674451152 34.9422990085463,-81.8222352380042 34.9422461947368,-81.8221955637091 34.9421805166084,-81.8221762273521 34.9421130602071,-81.8221890478642 34.9420591239261,-81.8222185315543 34.9420020588398,-81.8222454727805 34.9419587483983,-81.822285809529 34.941920569199,-81.8223325300962 34.9418870200319,-81.8223859400233 34.9418556227854,-81.8224489248093 34.9418128999731,-81.8224889264217 34.9417747192952,-81.8225302740814 34.9417376366815,-81.8225740312575 34.941708505597,-81.8226392132389 34.9416860981065,-81.8227094523873 34.9416702510572,-81.8227864887485 34.9416658989293,-81.8228348498407 34.9416639341387,-81.8228968301527 34.9416563842913,-81.8229812215637 34.9416536315488,-81.8230549527423 34.9416534238618,-81.8231380761429 34.9416580986245,-81.8232194820108 34.9416575637795,-81.8232928438399 34.9416532354431,-81.8233548699173 34.9416500811208,-81.8234023395482 34.9416593883636,-81.82343951217 34.9416737063409,-81.8234802746808 34.9416794817683,-81.8235349068754 34.9416706043068,-81.8235931802288 34.941659503549,-81.8236405399298 34.9416569969775,-81.8236965793202 34.9416555331113,-81.8237475695376 34.9416497036045,-81.8238201982566 34.9416385121233,-81.8238921916577 34.9416308969572,-81.8239663199906 34.941637001227,-81.8240490490361 34.941635086151,-81.8241106177492 34.941619296146,-81.8241444246516 34.9415959969038,-81.8241607212875 34.9415560513957,-81.824174650171 34.9415128259716,-81.8241928146145 34.941459130189,-81.8242100797266 34.9414158828489,-81.8242541178486 34.9413812508774,-81.8243048007717 34.9413438305302,-81.8243517117942 34.9413300566233,-81.8243868702635 34.941343015645,-81.8244348530726 34.9413707245006,-81.8244868998195 34.9414052733878,-81.824518160351 34.9414289691333,-81.8245546414079 34.9414751624828,-81.8246054003561 34.9415487353103,-81.8246311368598 34.9415878521527,-81.8246530675243 34.9416129846783,-81.8247018188107 34.9416514031375,-81.8247476391234 34.9416969834091,-81.8247994674966 34.941743075826,-81.8248332274519 34.9417840643498,-81.8248852076994 34.9418458145819,-81.824934652108 34.9419210427337,-81.8249981216802 34.9419975550838,-81.8250370651733 34.9420571906646,-81.8250854530628 34.9421219867635,-81.8251430609538 34.9421836998827,-81.8251858520964 34.9422268283043,-81.8252313768845 34.9422765296576,-81.8252672917721 34.9423328932185,-81.8253022497878 34.9423942035469,-81.8253353339191 34.942468167198,-81.8253718290079 34.9425497999166,-81.8254021368045 34.9426133392683,-81.8254344233312 34.9426741226851,-81.8254666938781 34.9427332539601,-81.8255065416135 34.9427824444997,-81.8255400037572 34.9428267338389,-81.8255842081174 34.9428778201499,-81.8256408355772 34.9429373391916,-81.8256822685747 34.9429777289466,-81.8257082790458 34.9430108017645,-81.8257336103372 34.9430416787671,-81.8257609092553 34.9430703506525,-81.8257750651025 34.9430850915879,-81.8257952681 34.9431039156628,-81.8258110686261 34.9431164500013,-81.8258333178953 34.9431399324781,-81.8258649783803 34.9431702197645,-81.8259014181912 34.9432120106005,-81.8259354822502 34.9432497040455,-81.8259628975986 34.9432899065212,-81.8259892758829 34.9433260037027,-81.8260200596741 34.9433694866658,-81.8260521628793 34.9434113096015,-81.8260805137539 34.9434449176776,-81.8261149022318 34.9434815065426,-81.8261468944552 34.9435123396113,-81.8261738958099 34.9435434813485,-81.8261955020325 34.9435697118018,-81.8262130957985 34.9435948761884,-81.8262360551933 34.9436224732376,-81.826258965969 34.9436453996324,-81.8262906571248 34.9436792614906,-81.8263142607661 34.9437046585126,-81.8263345120363 34.9437284262672,-81.826352704675 34.9437467154424,-81.8263746513097 34.943773495268,-81.8263948930204 34.9437961624858,-81.8264195842987 34.9438300713012,-81.8264513752272 34.9438746435997,-81.8264697377203 34.9439099650827,-81.8264863284543 34.9439348566034,-81.8265044093641 34.9439756720642,-81.8265328177195 34.9440150476043,-81.8265616080023 34.9440593674143,-81.8265820142224 34.9440990714652,-81.8265976579252 34.9441294640394,-81.8266160508529 34.9441683574787,-81.8266297897086 34.9442086534734,-81.8266405218333 34.9442484212283,-81.8266535134199 34.9442807548132,-81.8266641867073 34.9443150272181,-81.8266741438971 34.9443435336821,-81.8266827415089 34.9443701255482,-81.8266926943679 34.9443986356441,-81.8267000009587 34.9444293553335,-81.826705603613 34.9444565185708,-81.8267095818799 34.9444880885533,-81.8267108358966 34.9445141798446,-81.826714823152 34.9445471252775,-81.8267195042808 34.9445825332249,-81.8267249178393 34.9446242595499,-81.8267259463322 34.9446616160087,-81.8267279583435 34.9446967693174,-81.8267326475333 34.9447330055795,-81.8267345777794 34.9447601930016,-81.8267431916086 34.9447878844383,-81.82674791645 34.9448282397838,-81.8267519267913 34.944863107708,-81.8267565599301 34.9448935727796,-81.8267595548571 34.9449267969587,-81.8267661275706 34.9449509296561,-81.8267814312267 34.944980777295,-81.8267920140329 34.9450051593971,-81.826804229719 34.9450262325809,-81.8268237181155 34.9450403902155,-81.8268606130715 34.9450593799619,-81.8268971081139 34.9450731497193,-81.8269439294289 34.9450841076195,-81.8269940544974 34.9450920204949,-81.8270381659784 34.9450988723884,-81.8270926225825 34.945106204134,-81.8271420554447 34.9451113740967,-81.8271847716784 34.9451121922626,-81.8272261455616 34.9451121953973,-81.827265852302 34.9451122059015,-81.8273198191139 34.9451036106358,-81.8273564629692 34.9450973226064,-81.8273981156852 34.9450915522911,-81.8274537555173 34.9450832190574,-81.827510719123 34.9450732284414,-81.8275836363716 34.9450579099913,-81.8276319057719 34.9450463289198,-81.8276705071891 34.9450359076677,-81.8277260668174 34.9450193352991,-81.8277723377259 34.9450085885131,-81.827832282391 34.9449963787143,-81.8279028920566 34.9449835504956,-81.827980832118 34.9449692983677,-81.8280541232741 34.9449575484554,-81.8281337282365 34.9449432888532,-81.8282046898611 34.9449321022642,-81.8282856408771 34.9449189297647,-81.8283725628155 34.9449018743061,-81.8284524805812 34.9448856852789,-81.8285244014325 34.944870376461,-81.8285726625419 34.9448579676099,-81.8286322548595 34.9448441084187,-81.8286775199043 34.9448322745597,-81.8287271013153 34.9448187572437,-81.8287698057494 34.9448179272187,-81.8287847524996 34.9448115089218,-81.8287981108426 34.94481306934,-81.8288231129921 34.9448104317403,-81.8288381149542 34.9448092329827,-81.8288724270717 34.9448029635121,-81.8289094022431 34.9447972235838,-81.8289520561552 34.9447919950802,-81.8289863655705 34.9447854497728,-81.8290259848619 34.9447769485347,-81.8290646385653 34.9447714741977,-81.8290962903227 34.9447668654607,-81.8291302764743 34.9447619735834,-81.8291795834242 34.9447542285564,-81.8292335497893 34.9447456297621,-81.8292935217944 34.9447367145377,-81.8293461249599 34.9447248283435,-81.8293964243268 34.94471625371,-81.8294497234208 34.9447079341538,-81.8295043666922 34.9446996056909,-81.8295493715262 34.9446954611334,-81.8295949604661 34.9446830731537,-81.8296498458964 34.9446656769128,-81.8296999978399 34.9446419887352,-81.8297374527466 34.944616192314,-81.8297645613115 34.9445904615798,-81.8297936236921 34.9445597729323,-81.8298303030301 34.9445232641604,-81.8298572947623 34.9444854466249,-81.8298799619092 34.9444490304847,-81.8299032222352 34.9444051938338,-81.8299175211099 34.9443658097785,-81.8299373726746 34.9443145772728,-81.8299669262753 34.9442660289225,-81.8300083924185 34.9442069583534,-81.8300362558056 34.9441559459616,-81.8300599169387 34.9441184262236,-81.8300981773092 34.944073115711,-81.8301357844058 34.9440291814123,-81.8301741149998 34.9439907407561,-81.8302120945003 34.9439506510747,-81.8302689822236 34.9438994457251,-81.83032623227 34.9438507140521,-81.8303730882804 34.9437973792481,-81.8304077232109 34.9437564877654,-81.8304369741858 34.9437109645302,-81.830472842562 34.943659622333,-81.8305060508432 34.9436094019217,-81.8305502991354 34.9435621271769,-81.8305834955946 34.9435102554846,-81.8306170762039 34.943463875175,-81.830655565254 34.9434078482065,-81.8306777305644 34.9433544017628,-81.8307071617219 34.9432929389862,-81.8307293162619 34.9432383956164,-81.8307483125225 34.9432020035873,-81.8307659900626 34.9431669948945,-81.8307776217994 34.9431281791108,-81.830785714777 34.9431028498247,-81.8308110948669 34.9430708097731,-81.831094666573 34.9426917128491,-81.831420419045 34.9425586228779,-81.8314312416543 34.9425880607083,-81.8315383615052 34.942879419418,-81.831888926747 34.9427705219566,-81.832446316709 34.9429695258759,-81.8327081168009 34.9430629600119,-81.8329598971406 34.9437250983236,-81.8332765025478 34.9438586351464,-81.8335331551187 34.9439681995318,-81.8337784536845 34.9440764669124,-81.8340334281104 34.944184668611,-81.834127628854 34.9442299248252,-81.8342898086477 34.9443000048111,-81.8344634404349 34.9443812739411,-81.8344420686023 34.9444138361068,-81.8342996580566 34.9446238615165,-81.8342388231921 34.9447146530451,-81.8341409700116 34.9447397561636,-81.8341326214794 34.9447392636075,-81.833769888374 34.9447007356899,-81.8332489261954 34.9446544706247,-81.8332096075122 34.9450006312744,-81.8332154778157 34.9452593956948,-81.8333199017161 34.9455306993136,-81.8334155017357 34.9457866729908,-81.8333860721498 34.9457765358535,-81.8333571679141 34.9457653811651,-81.8333288547071 34.9457533202614,-81.8333011340569 34.9457402855289,-81.8332741062371 34.945726343906,-81.8333001439008 34.9458640916557,-81.8333538244592 34.9458837333244,-81.8334082896457 34.945901891492,-81.8334635049418 34.9459185068953,-81.8335193777448 34.9459271433723,-81.8335754450226 34.9459350691445,-81.833757678701 34.9461448607353,-81.8341520700964 34.9467233175057,-81.8342580977052 34.9468852582739,-81.834318302385 34.9468999715674,-81.8344254914626 34.9473506623196,-81.8347089089976 34.9472962975034,-81.8350306365643 34.9472350862634,-81.8352601265441 34.9471934486351,-81.8352144952187 34.9474737126597,-81.8351773855757 34.9477385378306,-81.8351438538817 34.9479937204946,-81.835668124078 34.9480685313417,-81.8356876698924 34.9481256551896,-81.8357105135236 34.948181934113,-81.8357365468866 34.9482372859029,-81.8357657103583 34.9482915469028,-81.8357979702717 34.948344632605,-81.8358332418078 34.9483963777178,-81.8358714398845 34.948446701681,-81.8359124944965 34.9484954985953,-81.8359562706895 34.9485426260372,-81.836002728296 34.9485880067525,-81.8360517053213 34.9486315318491,-81.8361031688603 34.9486730834621,-81.835834706154 34.948857849312,-81.836118324719 34.9491966392836,-81.8363435801755 34.94941681439,-81.83653887319 34.9495439769233,-81.8365922136671 34.9495787103811,-81.8367116770472 34.9495132408547,-81.8367390504189 34.9494982396641,-81.8368241518328 34.9494516027058,-81.8368304808767 34.9494773481314,-81.8369161624989 34.9497748744885,-81.8352322623805 34.9496915813271,-81.8352907921455 34.9499096082102,-81.8357403922308 34.9515536858814,-81.8360099547161 34.9516837681096,-81.8354493375855 34.9520551034773,-81.8354622393336 34.9524176734128,-81.8358202080878 34.9527150305952,-81.8371198469451 34.9527060966424,-81.8370733014159 34.9530965386341,-81.8369929043029 34.9537712335991,-81.8369909980027 34.9537872349767,-81.8369515291096 34.9541008671451,-81.8369470510086 34.9541364491996,-81.836868448734 34.9548109442263,-81.836822728509 34.9552032703402,-81.8367649512608 34.9557075521981,-81.8366912013655 34.956352286711,-81.8366653786615 34.9565425756618,-81.8366385713699 34.9567691407879,-81.8366333041553 34.9568136850266,-81.8365147529004 34.9578197471783,-81.8364955383188 34.9580039517679,-81.8364575705216 34.9583168582228,-81.8342659260324 34.9583361148876,-81.8324670223709 34.9583508058275,-81.8319549307719 34.958329583312,-81.83144433961 34.9582909035272,-81.8309361477436 34.9582348443704,-81.8298935686187 34.9581263507592,-81.8295193087127 34.9580952509323,-81.8291438072426 34.9580770424687,-81.8287677253251 34.9580717245971,-81.8283916720007 34.9580793455721,-81.8280163463688 34.9580998701343,-81.8276424112191 34.9581332443431,-81.8259227874399 34.9583047468602,-81.8225713881703 34.9586237453585,-81.8224064138443 34.9583006897114),(-81.830303189048 34.9459004372485,-81.8304604009059 34.945666965638,-81.8302302833793 34.9454728717278,-81.8301380731385 34.9457001461118,-81.829974120459 34.9457556306707,-81.8299917827193 34.9458563434542,-81.830136493862 34.9459141781899,-81.830303189048 34.9459004372485)),((-81.8135124516848 34.9554554221541,-81.813376448988 34.9554523933285,-81.8132407392515 34.9554603123829,-81.8131066170276 34.9554791195539,-81.8129753702008 34.9555086298347,-81.8128125475353 34.9555414713209,-81.8126483974012 34.9555694546556,-81.8124831274761 34.9555925415336,-81.8123520527538 34.9556068636502,-81.8123169476799 34.9556106990438,-81.811851966764 34.9551419807362,-81.8114685343449 34.9547559783334,-81.8139879552591 34.9540577611873,-81.8163450259272 34.9534143945277,-81.8171165114779 34.9540390809334,-81.8175356589211 34.953694305823,-81.8176186835948 34.9536248546985,-81.8178358993073 34.9534431549862,-81.8178783992328 34.95347025022,-81.8179227413929 34.9534952602652,-81.8179687749761 34.9535180995703,-81.8180163492311 34.9535386888933,-81.8180729193067 34.9535631324516,-81.8181271882018 34.9535908837171,-81.8181788743517 34.9536218075186,-81.8182277034031 34.9536557217662,-81.818327195149 34.953738662471,-81.8184233882806 34.9538242034444,-81.8185161669997 34.9539122571136,-81.8186054482277 34.9540027230733,-81.8206361911312 34.9562379373868,-81.8212522913305 34.9568707533099,-81.8213407790845 34.9569736668107,-81.8214263238508 34.9570782544303,-81.8215088877572 34.9571844497206,-81.82158841995 34.9572922025427,-81.8218723064787 34.9577307512423,-81.822016805825 34.9579542892153,-81.8221525804018 34.9581815040686,-81.8222794889056 34.9584121678048,-81.8223974041644 34.9586460424178,-81.8223379537563 34.9586535969256,-81.8222786876776 34.9586620993464,-81.8222196354421 34.9586715449804,-81.8221608156684 34.958681934607,-81.8208031758074 34.9589150066356,-81.8203685188647 34.958996221681,-81.8199378770164 34.9590908349736,-81.8195118563947 34.9591987082897,-81.8190910630026 34.9593196889914,-81.8186760895782 34.9594536101129,-81.8182675275077 34.9596002767616,-81.8182423650074 34.9594325785949,-81.8182309313551 34.9592638706173,-81.8182332655087 34.9590949106347,-81.8182493637282 34.9589264567267,-81.8182652036158 34.9588527318349,-81.81826381795 34.958777869016,-81.818245254694 34.9587045738861,-81.8182101896831 34.958635492077,-81.818170942544 34.9585999037864,-81.8181265467549 34.9585686900638,-81.8180777067242 34.958542349281,-81.8180253269702 34.9585213541688,-81.8180252148419 34.9585213071266,-81.8180090066245 34.9585161964877,-81.8179926155148 34.9585111943021,-81.8179761503913 34.9585063512388,-81.8179596190128 34.9585016771623,-81.8178427517799 34.9584559510802,-81.8177381070634 34.9583932985573,-81.8176492462866 34.9583158642334,-81.8175792079272 34.9582262937183,-81.8175596901222 34.9581976065603,-81.8175439765754 34.9581672838641,-81.817532245547 34.958135760736,-81.8175246339747 34.9581033878216,-81.8175050165739 34.9579992660626,-81.8174732091379 34.9578971985946,-81.8174295155751 34.9577981587186,-81.8173743521522 34.9577030775382,-81.817308231193 34.9576128566847,-81.8172317816864 34.9575283483539,-81.8171357887535 34.9574514233782,-81.8170420651157 34.9573726222147,-81.8169506649191 34.9572919967956,-81.8168616432738 34.9572095855265,-81.8167758041563 34.9571250839238,-81.816683404127 34.9570454058449,-81.8165848483273 34.9569708983744,-81.816480583434 34.9569019020174,-81.8163927503385 34.9568511885849,-81.81630082202 34.956805659397,-81.8162052635193 34.9567655160337,-81.8161065420723 34.9567309609629,-81.8160068221579 34.9566956010663,-81.8159142781805 34.9566488533296,-81.8158308254583 34.9565916752009,-81.8157581799313 34.9565252417572,-81.8156734530399 34.956433915943,-81.8155761501451 34.9563515178878,-81.8154676542676 34.9562792148751,-81.8153494992255 34.9562180316948,-81.8152233551876 34.9561688282032,-81.8150910068151 34.9561323030733,-81.8149320246258 34.9560884656865,-81.81478294964 34.9560252977672,-81.8146472226587 34.9559442516712,-81.8145279885705 34.9558472089373,-81.8143787860379 34.9557515368158,-81.8142198105791 34.9556672167493,-81.8140523188396 34.9555949165852,-81.8138776618639 34.9555352170311,-81.813697223645 34.9554885967312,-81.8135124516848 34.9554554221541)),((-81.8208588192711 34.9590440433607,-81.8214162449258 34.9589527535833,-81.8198612281758 34.9618372385494,-81.8198893246347 34.9622593316158,-81.8199043073087 34.9624982528483,-81.8199495706791 34.963325619104,-81.8199817887086 34.9637175366576,-81.8199856899177 34.9637647927014,-81.8200234205102 34.9642304686386,-81.820069214688 34.9648915228649,-81.8176464656523 34.9652243735898,-81.8175000094888 34.965244071416,-81.8172596988458 34.9652764512716,-81.8161159047241 34.965430223565,-81.8155162798278 34.9655107719696,-81.8150985309691 34.9637793357293,-81.8147536155181 34.9623328798213,-81.8149903006436 34.9621798595245,-81.815219820652 34.9620196103066,-81.8154418526048 34.9618523686598,-81.8156560844338 34.9616783619927,-81.817597812132 34.9601620849622,-81.8177649437152 34.9600456392675,-81.8179393986163 34.9599366081051,-81.8181206782953 34.9598354066234,-81.8183082687928 34.9597423256835,-81.8185623879265 34.9596442280624,-81.8188203729578 34.9595532327756,-81.8190819336835 34.9594694417469,-81.8193467732838 34.959392951537,-81.8197191870263 34.9592902133677,-81.8200956609405 34.9591977169266,-81.8204756799671 34.9591156277933,-81.8208588192711 34.9590440433607)),((-81.8132934221704 34.9555922634182,-81.8134140258759 34.9555879106961,-81.8135344753883 34.9555945700759,-81.8137725740108 34.9556544888934,-81.8140015581058 34.955734960968,-81.8142188253824 34.9558350685,-81.8144218896075 34.9559536694097,-81.8145121505651 34.9560268560228,-81.8146107604557 34.9560923384049,-81.8147167321924 34.9561494784597,-81.8148290531891 34.9561977202831,-81.8149466097711 34.9562365852472,-81.8150682585948 34.9562656985778,-81.8151799455001 34.9563008571182,-81.8152862532273 34.9563459196681,-81.8153858966493 34.9564003339325,-81.8154776630198 34.9564634470959,-81.815560435901 34.9565344894422,-81.8156332150856 34.9566125967576,-81.8157176979933 34.9566886233125,-81.8158138476037 34.9567545948481,-81.8159199049758 34.9568093050123,-81.8160339500949 34.9568517639321,-81.816067475076 34.9568653316348,-81.816101203591 34.9568785446656,-81.8161351367775 34.9568914075241,-81.8161692690492 34.9569039085284,-81.8163292867091 34.9569865193221,-81.8164792807471 34.9570809292237,-81.8166180424446 34.9571863357937,-81.8167443960321 34.9573018354264,-81.8168338923296 34.957385677775,-81.8169248304097 34.9574684614724,-81.8170171969799 34.9575501703772,-81.8171109645069 34.9576307875395,-81.8172037524398 34.9577427644671,-81.8172779251547 34.9578637816349,-81.8173322035287 34.9579917589141,-81.8173656542079 34.9581244832191,-81.8173995583566 34.9582155069136,-81.817447386918 34.9583021908346,-81.817508347682 34.9583830988266,-81.8175814248424 34.9584568845226,-81.8176562517513 34.9585129007766,-81.8177377675087 34.958562105884,-81.8178250676194 34.9586040307004,-81.8179172160137 34.9586382252172,-81.8179410693789 34.9586434176536,-81.8179641385074 34.9586506270658,-81.8179861761963 34.9586597640261,-81.8180069450696 34.9586707363389,-81.8180334337882 34.9586889103208,-81.8180564541017 34.9587100571021,-81.8180755089228 34.9587337193213,-81.8180902437836 34.9587593557608,-81.818093674393 34.9587978834291,-81.8180936757562 34.9588365118652,-81.818090234988 34.9588750401461,-81.818083378477 34.9589132526708,-81.8180653059475 34.9591023239612,-81.8180628900725 34.9592919701302,-81.8180761432736 34.959481308652,-81.8181050003616 34.9596694701203,-81.8179741246675 34.9597426357982,-81.8178456164947 34.9598185991018,-81.8177195594356 34.9598972864821,-81.8175960486245 34.9599785720352,-81.8174761349318 34.9600716636795,-81.8166386329285 34.9607114868818,-81.8157622511154 34.9614045810734,-81.8154813497983 34.9616231775666,-81.8153198064775 34.9617519100698,-81.8151539686083 34.9618768880751,-81.8149839655815 34.9619980169836,-81.8148099299804 34.9621151922606,-81.8117136675956 34.9615618747231,-81.8115115252601 34.9615173963574,-81.8113140007945 34.9614605660439,-81.8111222054333 34.9613917110332,-81.8109372170359 34.9613112191895,-81.8107600778519 34.961219534497,-81.8105917827084 34.961117181473,-81.8115611319734 34.9604402835757,-81.8124002354888 34.9598569425129,-81.8125129277793 34.9597785975916,-81.8132633802844 34.9592515553436,-81.81345418053 34.9591175934186,-81.8148366625745 34.9581466974002,-81.8136115597865 34.9569205134831,-81.8134198880972 34.9567332833312,-81.8125215459495 34.9558229794739,-81.8124403263644 34.9557406767844,-81.8124689491242 34.9557363938668,-81.8125955349832 34.955717459501,-81.8127501975967 34.9556918767424,-81.8129042529634 34.9556639370193,-81.8130576551863 34.9556336505461,-81.8131741416991 34.9556075717987,-81.8132934221704 34.9555922634182)),((-81.8092727780441 34.9620510109358,-81.8104220654321 34.9612377903975,-81.8106024246127 34.9613501845631,-81.8107925593767 34.9614510723898,-81.8109913792801 34.9615398921126,-81.8111977658173 34.9616161263203,-81.8114105421875 34.9616793463181,-81.8116284939448 34.9617291957714,-81.8127608225271 34.9619383607115,-81.8146181178261 34.9622628778569,-81.8131936170571 34.9633295810908,-81.8130473363435 34.9634422149728,-81.8129040515026 34.96355742613,-81.8127638200705 34.9636751628214,-81.8126267158953 34.9637953614826,-81.8125714803972 34.9638530724536,-81.8122974237073 34.9641267125992,-81.8121131326824 34.9644277689496,-81.8117593945874 34.9648473488033,-81.8115381250024 34.9650485612514,-81.811488153035 34.9650914092014,-81.8114317652222 34.9651284649213,-81.8113699364941 34.9651590839305,-81.8113037501589 34.9651827337207,-81.8108340343146 34.9653671712523,-81.8107853224219 34.9653871898462,-81.8107382837172 34.9654097493983,-81.8106931144521 34.9654347621115,-81.8106499832924 34.9654621178327,-81.8104185764462 34.9655962811545,-81.8098726273992 34.9659555079918,-81.8097841010626 34.9660130159689,-81.8094674596732 34.9662049507884,-81.8093368617614 34.9663046551746,-81.8092585723254 34.9663651262777,-81.8090652742856 34.9665505141341,-81.8090976682991 34.9665740591915,-81.8091185215635 34.9665892183539,-81.8060096128199 34.9670411581095,-81.8079992107726 34.963859151318,-81.8090134814052 34.9622276910591,-81.8092727780441 34.9620510109358)),((-81.8304551033934 34.9604965515598,-81.8304132615955 34.9604935734895,-81.8303615562436 34.9606241246055,-81.8293759374812 34.9605350476699,-81.8280851462502 34.9604175505523,-81.8280568144849 34.9603683447776,-81.8280520704649 34.960359020696,-81.8280410565322 34.9603373738063,-81.8280247985255 34.9603054209277,-81.8280159767408 34.9602859291861,-81.8280048409523 34.960261322078,-81.827995877074 34.9602415166918,-81.8279825235792 34.9602078500625,-81.8279740010207 34.9601863643036,-81.827970151093 34.9601766566446,-81.82794765062 34.9601110028373,-81.827801345595 34.959609226373,-81.8278019084935 34.9596042885303,-81.827835005385 34.9593140670288,-81.8278956213279 34.9588836189833,-81.8279861205787 34.9582409483388,-81.8279861533402 34.9582407146662,-81.8283174008983 34.9582178652682,-81.8283717172786 34.9582161052023,-81.8285105258036 34.9582116079505,-81.8287007173737 34.9582054445147,-81.8288147175112 34.9582058997144,-81.8289701761251 34.9582065200029,-81.8290843043734 34.9582069759145,-81.8292742587227 34.958214670939,-81.8294135174897 34.9582203120028,-81.8294674551683 34.9582224965859,-81.829849410405 34.95825192412,-81.8308967514956 34.9583697257426,-81.8313714805352 34.9584224011804,-81.8314243573285 34.9584282677634,-81.8315131202641 34.9584351233905,-81.8318650297202 34.9584623037945,-81.8319544282352 34.9584692089805,-81.8320082309184 34.9584715601224,-81.832486095712 34.9584924424493,-81.8342672605439 34.9584721021423,-81.8346129561175 34.9584689803114,-81.834701051343 34.9584681187348,-81.8356377221315 34.9584616101558,-81.8355251507926 34.9593338347434,-81.8363549895973 34.9592881973722,-81.8363425100849 34.959410814534,-81.8362735882517 34.9609105261648,-81.8304551033934 34.9604965515598)),((-81.8132601370197 34.9634822681749,-81.8145719917564 34.9625339407242,-81.8148746249864 34.9637880967521,-81.8152886400928 34.9655427457388,-81.8122061830114 34.9659597226131,-81.8122041340537 34.9661644156782,-81.8093399086426 34.9665720984475,-81.8092880187619 34.9665786019421,-81.8092721558378 34.9665807728456,-81.8092966176187 34.9665565115966,-81.8093399157666 34.966513572386,-81.8095582794132 34.9663400783025,-81.8098970613594 34.9661380384836,-81.8099854586232 34.9660808837002,-81.8105151471326 34.9657281250645,-81.8107227772015 34.9656036056664,-81.8107781689356 34.9655713447974,-81.8108352468351 34.9655411497727,-81.8108939086104 34.9655130870509,-81.8109540299372 34.9654872088111,-81.8113876157711 34.9653176579658,-81.8114685369752 34.9652873554116,-81.8115446475191 34.9652495403224,-81.8116149207492 34.9652047159997,-81.8116784153645 34.9651534942606,-81.8118923533684 34.9649589603853,-81.8122721242039 34.9645118190448,-81.8124599805629 34.9642126602232,-81.8128493157719 34.9638223487447,-81.8128833856852 34.9637946218535,-81.8129741008035 34.9637137410492,-81.8130671676415 34.9636346918557,-81.8131625318608 34.9635575169847,-81.8132601370197 34.9634822681749)),((-81.8179421342881 34.9533506997645,-81.8183973340412 34.95297381122,-81.8183822495297 34.9535906992741,-81.818305366582 34.9535346237163,-81.8182230364693 34.9534840594906,-81.8181358406774 34.9534393642491,-81.8180443932087 34.9534008620822,-81.818016083568 34.9533925096252,-81.8179893135787 34.9533812338742,-81.81796452735 34.953367223028,-81.8179421342881 34.9533506997645)),((-81.8277481310616 34.960021882712,-81.8277511125406 34.9599982614356,-81.8277619491738 34.9600335446306,-81.8278891693436 34.9604114041304,-81.8277003181635 34.9604006602766,-81.8277095402439 34.9603276033911,-81.827709654086 34.9603267003638,-81.827746821724 34.9600322508544,-81.8277481310616 34.960021882712)))