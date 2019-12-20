package year2019.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class IntcodeComputer {
    private static final Logger LOGGER = Logger.getLogger(IntcodeComputer.class.getSimpleName());
    Map<Long, String> instructions;
    long index = 0;
    long relativeBase;
    List<Long> inputs;
    List<IntcodeComputerOutput> outputs = new ArrayList<>();
    boolean finished;


    public IntcodeComputer(Map<Long, String> instructions) {
        this.instructions = instructions;
    }

    public Map<Long, String>  getInstructions() {
        return instructions;
    }

    public void setInstructions(Map<Long, String>  instructions) {
        this.instructions = instructions;
    }

    public List<Long> getInputs() {
        return inputs;
    }

    public void setInputs(List<Long> inputs) {
        this.inputs = inputs;
    }

    public List<IntcodeComputerOutput> executeInstructions() {
        finished = false;
        outputs = new ArrayList<>();
        while (!finished) {
            String opCode;
            String operation = instructions.get(index);

            if (operation.equals("99")) {
                finished = true;
            }

            if (!finished) {
                Long param1 = instructions.get(index + 1) != null ? Long.parseLong(instructions.get(index + 1)) : null;
                Long param2 = instructions.get(index + 2) != null ? Long.parseLong(instructions.get(index + 2)) : null;
                Long param3 = instructions.get(index + 3) != null ? Long.parseLong(instructions.get(index + 3)) : null;

                if (operation.length() > 1) {
                    opCode = operation.substring(operation.length() - 2).replace("0", "");
                } else {
                    opCode = operation;
                }

                Long[] params = new Long[3];
                params[0] = param1;
                params[1] = param2;
                params[2] = param3;

                Long[] paramModes = new Long[3];
                if (operation.length() > 1) {
                    String[] parameterModesString = operation.substring(0, operation.length() - 2).split("");


                    for (int i = 2; i >= 0; i--) {
                        if (i >= parameterModesString.length) {
                            paramModes[i] = 0L;
                        } else {
                            paramModes[i] = Long.parseLong(parameterModesString[parameterModesString.length - (i + 1)]);
                        }
                    }
                } else {
                    paramModes[0] = 0L;
                    paramModes[1] = 0L;
                    paramModes[2] = 0L;
                }

                executeOperation(opCode, params, paramModes);
            }
        }

        return outputs;
    }

    private void executeOperation(String opCode, Long[] params, Long[] paramModes) {
        switch (opCode) {
            case "1":
                operation1(params, paramModes);
                break;
            case "2":
                operation2(params, paramModes);
                break;
            case "3":
                operation3(params, paramModes);
                break;
            case "4":
                operation4(params, paramModes);
                break;
            case "5":
                operation5(params, paramModes);
                break;
            case "6":
                operation6(params, paramModes);
                break;
            case "7":
                operation7(params, paramModes);
                break;
            case "8":
                operation8(params, paramModes);
                break;
            case "9":
                operation9(params, paramModes);
                break;
            case "99":
                finished = true;
                break;
            default:
                LOGGER.severe("Onbekende operatie, dit kan niet goed zijn!");

        }
    }

    private void operation1(Long[] params, Long[] paramModes) {
        long result;
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);
        long outputValue = getOutputValue(params[2], paramModes[2]);

        result = value1 + value2;
        instructions.put(outputValue, String.valueOf(result));
        index += 4;
    }

    private void operation2(Long[] params, Long[] paramModes) {
        long result;
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);
        long outputValue = getOutputValue(params[2], paramModes[2]);

        result = value1 * value2;
        instructions.put(outputValue, String.valueOf(result));
        index += 4;
    }

    private void operation3(Long[] params, Long[] paramModes) {
        long value = getOutputValue(params[0], paramModes[0]);
        if (inputs.isEmpty()) {
            finished = true;
        } else {
            instructions.put(value, String.valueOf(inputs.remove(0)));
            index += 2;
        }
    }

    private void operation4(Long[] params, Long[] paramModes) {
        long value = getValue(params[0], paramModes[0]);

        IntcodeComputerOutput out = new IntcodeComputerOutput();
        out.setOpCode("4");
        out.setLoop(index);
        out.setOutputValue(value);
        outputs.add(out);
        index += 2;
    }

    private void operation5(Long[] params, Long[] paramModes) {
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);

        if (value1 != 0) {
            index = value2;
        } else {
            index += 3;
        }
    }

    private void operation6(Long[] params, Long[] paramModes) {
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);

        if (value1 == 0) {
            index = value2;
        } else {
            index += 3;
        }
    }

    private void operation7(Long[] params, Long[] paramModes) {
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);
        long outputValue = getOutputValue(params[2], paramModes[2]);

        if (value1 < value2) {
            instructions.put(outputValue, String.valueOf(1));
        } else {
            instructions.put(outputValue, String.valueOf(0));
        }
        index += 4;
    }

    private void operation8(Long[] params, Long[] paramModes) {
        long value1 = getValue(params[0], paramModes[0]);
        long value2 = getValue(params[1], paramModes[1]);
        long outputValue = getOutputValue(params[2], paramModes[2]);

        if (value1 == value2) {
            instructions.put(outputValue, String.valueOf(1));
        } else {
            instructions.put(outputValue, String.valueOf(0));
        }
        index += 4;
    }

    private void operation9(Long[] params, Long[] paramModes) {
        long value = getValue(params[0], paramModes[0]);
        relativeBase += value;
        index += 2;
    }

    private long getValue(long param, long mode) {
        Long value = null;
        if (mode == 0L) {
            value = Long.valueOf(instructions.getOrDefault(param, "0"));
        } else if (mode == 1L) {
            value = Long.valueOf(param);
        } else if (mode == 2L) {
            value = Long.valueOf(instructions.getOrDefault(param + relativeBase, "0"));
        } else {
            LOGGER.severe("Onbekende parameter mode, dit kan niet goed zijn!");
        }

        return value;
    }

    private long getOutputValue(long param, long mode) {
        Long value = null;
        if (mode == 0L) {
            value = param;
        } else if (mode == 1L) {
            value = param;
        } else if (mode == 2L) {
            value = param + relativeBase;
        } else {
            LOGGER.severe("Onbekende parameter mode, dit kan niet goed zijn!");
        }

        return value;
    }

    public static class IntcodeComputerOutput {
        private String opCode;
        private long outputValue;
        private long loop;

        public IntcodeComputerOutput() {
        }

        public IntcodeComputerOutput(String opCode, long outputValue) {
            this.opCode = opCode;
            this.outputValue = outputValue;
            this.loop = 0;
        }

        public String getOpCode() {
            return opCode;
        }

        public void setOpCode(String opCode) {
            this.opCode = opCode;
        }

        public long getOutputValue() {
            return outputValue;
        }

        public void setOutputValue(long outputValue) {
            this.outputValue = outputValue;
        }

        public long getLoop() {
            return loop;
        }

        public void setLoop(long loop) {
            this.loop = loop;
        }
    }



//    if (opCode.equals("1") || opCode.equals("2")) {
//        long result;
//        if (opCode.equals("1")) {
//            result = value1 + value2;
//        } else {
//            result = value1 * value2;
//        }
//
//        instructions.put(output, String.valueOf(result));
//        index += 4;
//    } else if (opCode.equals("3")) {
//        instructions.put(param1 + relativeBase, String.valueOf(inputs.remove(0)));
//        index += 2;
//    } else if (opCode.equals("4")) {
//        outputs.add(new IntcodeComputerOutput(opCode, value1));
//        index += 2;
//    } else if (opCode.equals("5")) {
//        if (value1 != 0) {
//            index = value2;
//        } else {
//            index += 3;
//        }
//    } else if (opCode.equals("6")) {
//        if (value1 == 0) {
//            index = value2;
//        } else {
//            index += 3;
//        }
//    } else if (opCode.equals("7")) {
//        if (value1 < value2) {
//            instructions.put(output, String.valueOf(1));
//        } else {
//            instructions.put(output, String.valueOf(0));
//        }
//        index += 4;
//    } else if (opCode.equals("8")) {
//        if (value1 == value2) {
//            instructions.put(output, String.valueOf(1));
//        } else {
//            instructions.put(output, String.valueOf(0));
//        }
//        index += 4;
//    } else if (opCode.equals("9")) {
//        relativeBase += value1;
//        index += 2;
//    }



    //                Long value1 = null;
//                Long value2 = null;
//
//                Long mode = 0L;
//                if (inParamMode) {
//                     mode = Long.parseLong(parameterModes[parameterModes.length - 1]);
//                }
//
//                Long value1PositionMode = instructions.get(param1) != null ? Long.parseLong(instructions.get(param1)) : null;
//                Long value1ParamMode = inParamMode ? param1 : instructions.get(param1) != null ? Long.parseLong(instructions.get(param1)) : null;
//                Long value1RelativeMode = inParamMode ? instructions.get(param1 + relativeBase) != null ? Long.parseLong(instructions.get(param1 + relativeBase)) : null : instructions.get(param1) != null ? Long.parseLong(instructions.get(param1)) : null;
//                value1 = inParamMode ? (mode == 0 ? value1PositionMode : (mode == 1 ? value1ParamMode : value1RelativeMode)) : value1PositionMode;
//                if (inParamMode && parameterModes.length > 1) {
//                    mode = Long.parseLong(parameterModes[parameterModes.length - 2]);
//                    Long value2PositionMode = instructions.get(param2) != null ? Long.parseLong(instructions.get(param2)) : null;
//                    Long value2ParamMode = inParamMode ? param2 : instructions.get(param2) != null ? Long.parseLong(instructions.get(param2)) : null;
//                    Long value2RelativeMode = inParamMode ? instructions.get(param2 + relativeBase) != null ? Long.parseLong(instructions.get(param2 + relativeBase)) : null : instructions.get(param2) != null ? Long.parseLong(instructions.get(param2)) : null;
//
//                    value2 = inParamMode ? (mode == 0 ? value2PositionMode : (mode == 1 ? value2ParamMode : value2RelativeMode)) : value2PositionMode;
//                } else {
//                    if (!(opCode.equals("3") || opCode.equals("4") || opCode.equals("9"))) {
//                        value2 = Long.parseLong(instructions.get(param2));
//                    }
//                }
//
//                if (value1 == null) {
//                    value1 = 0L;
//                }
//
//                if (value2 == null) {
//                    value2 = 0L;
//                }
}
