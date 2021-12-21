package year2021.day16;

import base.Base;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static long versionTotal;
    private int pointer;
    private String input;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        //String inputTest = "D2FE28";
        String inputTest = "C20D718021600ACDC372CD8DE7A057252A49C940239D68978F7970194EA7CCB310088760088803304A0AC1B100721EC298D3307440041CD8B8005D12DFD27CBEEF27D94A4E9B033006A45FE71D665ACC0259C689B1F99679F717003225900465800804E39CE38CE161007E52F1AEF5EE6EC33600BCC29CFFA3D8291006A92CA7E00B4A8F497E16A675EFB6B0058F2D0BD7AE1371DA34E730F66009443C00A566BFDBE643135FEDF321D000C6269EA66545899739ADEAF0EB6C3A200B6F40179DE31CB7B277392FA1C0A95F6E3983A100993801B800021B0722243D00042E0DC7383D332443004E463295176801F29EDDAA853DBB5508802859F2E9D2A9308924F9F31700AA4F39F720C733A669EC7356AC7D8E85C95E123799D4C44C0109C0AF00427E3CC678873F1E633C4020085E60D340109E3196023006040188C910A3A80021B1763FC620004321B4138E52D75A20096E4718D3E50016B19E0BA802325E858762D1802B28AD401A9880310E61041400043E2AC7E8A4800434DB24A384A4019401C92C154B43595B830002BC497ED9CC27CE686A6A43925B8A9CFFE3A9616E5793447004A4BBB749841500B26C5E6E306899C5B4C70924B77EF254B48688041CD004A726ED3FAECBDB2295AEBD984E08E0065C101812E006380126005A80124048CB010D4C03DC900E16A007200B98E00580091EE004B006902004B00410000AF00015933223100688010985116A311803D05E3CC4B300660BC7283C00081CF26491049F3D690E9802739661E00D400010A8B91F2118803310A2F43396699D533005E37E8023311A4BB9961524A4E2C027EC8C6F5952C2528B333FA4AD386C0A56F39C7DB77200C92801019E799E7B96EC6F8B7558C014977BD00480010D89D106240803518E31C4230052C01786F272FF354C8D4D437DF52BC2C300567066550A2A900427E0084C254739FB8E080111E0";
        input = hexToBinary(inputTest);
        Packet finalPacket = decryptPackage();

        System.out.println(finalPacket.sumVersion());

    }

    private Packet decryptPackage() {
        Packet packet = new Packet();

        packet.setVersion(binaryToDecimal(packet, input, 3));
        packet.setTypeId(binaryToDecimal(packet, input, 3));

        if (packet.getTypeId() == 4) {
            packet.setLiteralValue(calculateLiteral(packet));
        } else {
            // get length Type id
            packet.setTypeLengthId(binaryToDecimal(packet, input, 1));
            Packet subPacket;

            if (packet.getTypeLengthId() == 0) {
                // length is a 15 bit number
                int packetsLength = 0;
                int length = binaryToDecimal(packet, input, 15);

                while (true) {
                    subPacket = decryptPackage();
                    packet.getSubPackets().add(subPacket);
                    packet.setPackageLength(packet.getPackageLength() + subPacket.getPackageLength());
                    packetsLength += subPacket.getPackageLength();
                    if (packetsLength >= length) break;
                }
            } else {
                int numPackets = binaryToDecimal(packet, input, 11);
                for (int i = 0; i < numPackets; i++) {
                    subPacket = decryptPackage();
                    packet.setPackageLength(packet.getPackageLength() + subPacket.getPackageLength());
                    packet.getSubPackets().add(subPacket);
                }
            }

            // calculate the value
            if (packet.getTypeId() == 0) {
                // sum packet
                for (Packet sp : packet.getSubPackets()) {
                    packet.setLiteralValue(packet.getLiteralValue().add(sp.getLiteralValue()));
                }
            } else if (packet.getTypeId() == 1) {
                // product packet
                if (packet.getSubPackets().size() == 1) packet.setLiteralValue(packet.getSubPackets().get(0).getLiteralValue());
                else {
                    packet.setLiteralValue(BigInteger.ONE);
                    for (Packet sp : packet.getSubPackets()) {
                        packet.setLiteralValue(packet.getLiteralValue().multiply(sp.getLiteralValue()));
                    }
                }
            } else if (packet.getTypeId() == 2) {
                // min packet
                BigInteger min = BigInteger.valueOf(Long.MAX_VALUE);
                for (Packet sp : packet.getSubPackets()) {
                    packet.setLiteralValue(min.min(sp.getLiteralValue()));
                    min = min.min(sp.getLiteralValue());
                }
            } else if (packet.getTypeId() == 3) {
                // max packet
                BigInteger max = BigInteger.valueOf(Long.MIN_VALUE);
                for (Packet sp : packet.getSubPackets()) {
                    packet.setLiteralValue(max.max(sp.getLiteralValue()));
                    max = max.max(sp.getLiteralValue());
                }
            } else if (packet.getTypeId() == 5 || packet.getTypeId() == 6 || packet.getTypeId() == 7) {
                int comp = packet.getSubPackets().get(0).getLiteralValue().compareTo(packet.getSubPackets().get(1).getLiteralValue());

                switch (packet.getTypeId()) {
                    case 5:
                        packet.setLiteralValue(comp > 0 ? BigInteger.ONE : BigInteger.ZERO);
                        break;
                    case 6:
                        packet.setLiteralValue(comp < 0 ? BigInteger.ONE : BigInteger.ZERO);
                        break;
                    case 7:
                        packet.setLiteralValue(comp == 0 ? BigInteger.ONE : BigInteger.ZERO);
                        break;
                }
            }

        }
        return packet;
    }

    private BigInteger calculateLiteral(Packet packet) {
        boolean lastValue = false;
        StringBuilder sb = new StringBuilder();

        while (!lastValue) {
            String val = input.substring(pointer, pointer + 5);
            pointer += 5;
            packet.setPackageLength(packet.getPackageLength() + 5);
            if (val.charAt(0) == '0') {
                lastValue = true;
            }
            sb.append(val.substring(1));
        }
        return BigInteger.valueOf(Long.parseLong(sb.toString(), 2));
    }

    private String hexToBinary(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(String.format("%4s", new BigInteger(String.valueOf(c), 16).toString(2)).replace(" ", "0"));
        }
        return sb.toString();
    }

    private int binaryToDecimal(Packet p, String input, int length) {
        String stringVal = input.substring(pointer, pointer + length);
        pointer += length;
        p.setPackageLength(p.getPackageLength() + length);
        return Integer.parseInt(stringVal, 2);
    }

    @Override
    public void part2() throws IOException {

    }

    class Packet {
        private int version;
        private int typeId;
        private Integer typeLengthId;
        private Integer packageLength;
        private BigInteger literalValue;
        private List<Packet> subPackets;

        public Packet() {
            this.packageLength = 0;
            this.subPackets = new ArrayList<>();
            this.literalValue = BigInteger.ZERO;
        }

        public List<Packet> getSubPackets() {
            return subPackets;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public Integer getTypeLengthId() {
            return typeLengthId;
        }

        public void setTypeLengthId(Integer typeLengthId) {
            this.typeLengthId = typeLengthId;
        }

        public Integer getPackageLength() {
            return packageLength;
        }

        public void setPackageLength(Integer packageLength) {
            this.packageLength = packageLength;
        }

        public BigInteger getLiteralValue() {
            return literalValue;
        }

        public void setLiteralValue(BigInteger literalValue) {
            this.literalValue = literalValue;
        }

        public long sumVersion() {
            long sum = getVersion();
            for (Packet sp : getSubPackets()) {
                sum += sp.sumVersion();
            }

            return sum;
        }
    }
}
