package sample;

import java.util.regex.Pattern;

public class Converter {

    public int stateFrom;
    public int stateTo;
    public String toBinary(double d, int precision) {
        long wholePart = (long) d;
        if (!fractionalToBinary(d - wholePart, precision).isEmpty())
        return wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, precision);
        else
            return wholeToBinary(wholePart);
    }
    public String binaryToDecimal(String binary, int len)
    {
        binary=binary.replace(",",".");
        Pattern pattern = Pattern.compile("^[0-1,.]+$");
        if (!pattern.matcher(binary).matches())
        {
            throw new IllegalArgumentException("Incorrect input.");
        }
        else if (!findCountOfPoints(binary))
        {
            throw new IllegalArgumentException("Multiplie points.");
        }
        int point = binary.indexOf('.');
        if (point == -1)
            point = len;
        double intDecimal = 0,  fracDecimal = 0, twos = 1;
        for(int i = point - 1; i >= 0; i--)
        {
            intDecimal += (binary.charAt(i) - '0') * twos;
            twos *= 2;
        }
        twos = 2;
        for(int i = point + 1; i < len; i++)
        {
            fracDecimal += (binary.charAt(i) - '0') / twos;
            twos *= 2.0;
        }
        if (point!=-1)
            return String.valueOf(intDecimal + fracDecimal);
        else
            return String.valueOf(intDecimal);

    }
    public  String toHeximal(double d, int precision)
    {
        int[] decimals={10,11,12,13,14,15};
        char[] dHex={'a','b','c','d','e','f'};
        double somedouble=d-(int)d;
        StringBuilder stringBuilder=new StringBuilder();
        double temp;
        for (int i=0;i<precision;i++)
        {
            temp=round(somedouble*16,10);
            if ((int)temp>=0)
            {
                if ((int)temp>9)
                {
                    for (int j=0;j<decimals.length;j++)
                    {
                        if ((int)temp==decimals[j])
                        {
                            stringBuilder.append(dHex[j]);
                        }
                    }
                }
                else
                {
                    stringBuilder.append((int)temp);
                }
                somedouble=round((temp-(int)temp),10);
            }
        }
        int prec=0;
        char[] chars=stringBuilder.toString().toCharArray();
        for (int i=0;i<chars.length;i++)
        {
            if (chars[i]=='0'&&(i+1)<=chars.length-1)
            {
                if (chars[i+1]!='0')
                {
                    prec=0;
                    continue;
                }
                else if (chars[i+1]=='0')
                {
                    prec++;
                }
            }
        }
        if (prec>10)
        {
           StringBuilder stringBuilder1=new StringBuilder(stringBuilder.substring(0,stringBuilder.length()-prec));
           stringBuilder.setLength(0);
           stringBuilder.append(stringBuilder1.toString());
        }

        if (!stringBuilder.toString().isEmpty())
            return Integer.toHexString((int) d) +"."+stringBuilder;
        else
            return Integer.toHexString((int) d);
    }
    public  String hexToDecimal(String hex, int len)
    {
        double temp=0;
        hex=hex.replace(",",".");
        Pattern pattern = Pattern.compile("^[0-9,.a-fA-F]+$");
        if (!pattern.matcher(hex).matches())
        {
            throw new IllegalArgumentException("Incorrect input.");
        }
        else if (!findCountOfPoints(hex))
        {
            throw new IllegalArgumentException("Multiplie points.");
        }
        int[] decimals={10,11,12,13,14,15};
        char[] dHex={'a','b','c','d','e','f'};
        int point = hex.indexOf('.');
        if (point == -1)
            point = len;
        int s=0;
        for(int i = point + 1; i < len; i++)
        {
            s--;
            if (Character.isDigit(hex.charAt(i)))
                temp+=Character.getNumericValue(hex.charAt(i))*Math.pow(16,s);
            else
            for (int j=0;j<decimals.length;j++)
            {
                if ( hex.charAt(i)==dHex[j])
                {
                    temp+=decimals[j]*Math.pow(16,s);
                }
            }
        }
        if (point!=-1)
          return String.valueOf(Integer.parseInt(hex.substring(0,point),16)+temp);
        else
            return String.valueOf(Integer.parseInt(hex));
    }
    public String toOctal(double d,int precision)
    {
        double temp = 0;
        double somedouble=d-(int)d;
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<precision;i++)
        {
            temp=round(somedouble*8,10);
            if ((int)temp>=0)
            {
                stringBuilder.append((int)temp);
            }
            somedouble=round((temp-(int)temp),10);
        }
        int prec=0;
        char[] chars=stringBuilder.toString().toCharArray();
        for (int i=0;i<chars.length;i++)
        {
            if (chars[i]=='0'&&(i+1)<=chars.length-1)
            {
                if (chars[i+1]!='0')
                {
                    prec=0;
                    continue;
                }
                else if (chars[i+1]=='0')
                {
                    prec++;
                }
            }
        }
        if (prec>10)
        {
            StringBuilder stringBuilder1=new StringBuilder(stringBuilder.substring(0,stringBuilder.length()-prec));
            stringBuilder.setLength(0);
            stringBuilder.append(stringBuilder1.toString());
        }

        if (!stringBuilder.toString().isEmpty())
            return Integer.toOctalString((int) d) +"."+stringBuilder;
        else
            return Integer.toOctalString((int) d);

    }
    public  String octalToDecimal(String octal,int len)
    {
        double temp=0;

        octal=octal.replace(",",".");
        Pattern pattern = Pattern.compile("^[0-7,.]+$");
        if (!pattern.matcher(octal).matches())
        {
            throw new IllegalArgumentException("Incorrect input.");
        }
        else if (!findCountOfPoints(octal))
        {
            throw new IllegalArgumentException("Multiplie points.");
        }
        int point = octal.indexOf('.');
        if (point == -1)
            point = len;
       //fract
        int s=0;
        for(int i = point + 1; i < len; i++)
        {
            s--;
            temp+=Character.getNumericValue(octal.charAt(i))*Math.pow(8,s);

        }
        if (point!=-1)
            return String.valueOf(Integer.parseInt(octal.substring(0,point),8)+temp);
        else
            return String.valueOf(Integer.parseInt(octal));
    }
    private  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    private String wholeToBinary(long l) {
        return Long.toBinaryString(l);
    }
    private String fractionalToBinary(double d, int precision) {
        StringBuilder binary = new StringBuilder();
        while (d > 0 && binary.length() < precision) {
            double r = d * 2;
            if (r >= 1) {
                binary.append(1);
                d = r - 1;
            } else {
                binary.append(0);
                d = r;
            }
        }
        return binary.toString();
    }
    private boolean findCountOfPoints(String s)
    {
        s = s.replace(",",".");
        char[] chars=s.toCharArray();
        int point=0;
        for (char aChar : chars) {
            if (aChar == '.') {
                point++;
                if (point > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
