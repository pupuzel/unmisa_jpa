package com.jock.unmisa.utils;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/***
	 * null && 공백 체크
	 * @param String
	 * @return String
	 */
	public static String checkNull(String str){
		if(str == null){
			return "";
		}
		return str.trim();
	}

	/***
	 * null && 공백 체크 and 특정 문자로 채우기
	 * @param String
	 * @return String
	 */
	public static String checkNullAndSetString(String str, String str2){

		if(str2 == null || str2.equals("")){
			str2 = "";
		}

		if (str == null || str.equals(""))
		{
			str = str2;
		}

		return str.trim();
	}

	/**
	 * 숫자체크
	 *
	 * @param num
	 * @return boolean
	 */
	public static boolean chkNum(String num){

		boolean chk = true;

		if(num == null) num = "";

		Pattern patt = Pattern.compile("^[0-9]*[0-9]$");
		Matcher match = patt.matcher(num);

		if(num == null || !match.find())
		{
			chk = false;
		}
		else
		{
			chk = true;
		}

		return chk;
	}
	//숫자만 뽑아내기
	public static String getOnlyNumber(String str){

		if(str ==null)
			return str;

		StringBuffer sb = new StringBuffer();
		int length = str.length();
		for(int i=0; i < length; i++){
			char curChar = str.charAt(i);
			if(Character.isDigit(curChar))
				sb.append(curChar);
		}
		return sb.toString();
	}

	/**
	 * 문자가 길경우에 특정 바이트 단위 길이로 자른다
	 *
	 * @param str : 문자열
	 * @param byteSize : 남길 문자열의 길이
	 * @return String : 자르고 남은 문자열
	 * @throws Exception
	 */
	public static String getStringOfList( String str, int byteSize ) throws Exception {
		int rSize = 0;
		int len = 0;

		if( str.getBytes().length > byteSize ) {
			for( ; rSize < str.length(); rSize++ ) {
				if( str.charAt( rSize ) > 0x007F )
					len += 2;
				else
					len++;

				if( len > byteSize ) break;
			}
			str = str.substring( 0, rSize );
		}

		return str;

	}

	/**
	 * array의 elements들을 separator를 구분자로 문자열 합치기
	 *
	 * @param array
	 * @param separator
	 * @return String
	 */
	public static String join( Object[] array, String separator ) {
		StringBuffer buf = new StringBuffer();

		for( int i = 0; i < array.length; i++ ) {
			if( i > 0 ) buf.append( separator );

			buf.append( array[i].toString() );
		}
		return buf.toString();
	}

	/**
	 * src를 prefix와 postfix로 감싼다
	 *
	 * @param src
	 * @param prefix
	 * @param postfix
	 * @return String
	 */
	public static String wrap( String src, String prefix, String postfix ) {
		return prefix + src + postfix;
	}

	/**
	 * 줄바꿈 처리 \n 을
	 * <pre><BR></pre>
	 * 로 변경
	 *
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String setHTML( String str ) throws Exception {
		String returnStr = "";
		if( str == null ) return "";

		returnStr = StringUtil.replace( str, "\n", "<BR>" );
		returnStr = returnStr.replaceAll( "'", "′" );

		return returnStr;
	}

	/**
	 * 줄바꿈 처리 \n 을 공백으로 변경
	 *
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String setSpace( String str ) throws Exception {
		if( str == null ) return "";

		str = str.replace( '\t', ' ' );
		str = str.replace( '\n', ' ' );
		str = str.replace( '\r', ' ' );
		str = str.trim();

		return str;
	}

	/**
	 * 디비에 들어갈때.. 에러방지 ' or -- 를 처리
	 *
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String setDB( String str ) throws Exception {
		String returnStr = "";
		if( str == null ) return "";

		returnStr = StringUtil.replace( str, "'", "''" );
		returnStr = StringUtil.replace( returnStr, "--", "- " );
		returnStr = returnStr.trim();

		return returnStr;
	}

	/**
	 * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 return
	 *
	 * @param str : 대상 스트링
	 * @return String
	 */
	public static String nvl( String str ) {
		if( str == null )
			return "";
		else
			return str;
	}

	/**
	 * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 trim한 후 return
	 *
	 * @param str : trim한 대상 스트링
	 * @return String
	 */
	public static String trim( String str ) throws Exception {
		String sTmp = str;

		if( str == null ) {
			sTmp = "";
		} else if( !"".equals( str ) && str.length() > 0 ) {
			sTmp = str.trim();
		}

		return sTmp;
	}

	/**
	 * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 trim한 후 return
	 *
	 * @param str : trim한 대상 스트링
	 * @param value : null일 경우 대체 string
	 * @return String
	 */
	public static String trim( String str, String value ) throws Exception {
		String sTmp = str;

		if( str == null ) {
			sTmp = value;
		} else if( !"".equals( str ) && str.length() > 0 ) {
			sTmp = str.trim();
		}

		return sTmp;
	}

	/**
	 * 대상 String배열의 값이 null일 경우 ""를, null이 아닐 경우 원래값 그대로를 가진 String 배열을 return
	 *
	 * @param str : 대상 스트링
	 * @return String[]
	 */
	public static String[] nvl( String str[] ) throws Exception {
		String[] new_str = null;

		new_str = new String[str.length];
		for( int i = 0; i < str.length; i++ )
			new_str[i] = nvl( str[i] );

		return new_str;
	}

	/**
	 * 대상 String이 null일 경우 대체 String을, null이 아닐 경우 대상 String을 return
	 *
	 * @param str : 대상 문자
	 * @param val : null일 경우 대체될 문자
	 * @return String
	 */
	public static String nvl( String str, String val ) {
		if( str == null )
			return val;
		else if( "".equals( str ) )
			return val;
		else
			return str;
	}

	/**
	 * 대상 String(str1)이 String(str2)일 경우 대체 String(val1)을, 아닐 경우 String(val2)을 return
	 *
	 * @param str : 대상 문자
	 * @param val : null일 경우 대체될 문자
	 * @return String
	 */
	public static String snvl( String str1, String str2, String val1, String val2 )
			throws Exception {
		if( str1.equals( str2 ) )
			return val1;
		else
			return val2;
	}

	/**
	 * 대상 String이 null일 경우 대체 int 를, null이 아닐 경우 대상 String을 int 로 변환하여 return
	 *
	 * @param str : 대상 문자
	 * @param val : null일 경우 대체될 문자
	 * @return int
	 * @throws Exception
	 */
	public static int nvl( String str, int val ) throws Exception {
		int iRs = 0;

		if( str == null )
			iRs = val;
		else
			iRs = Integer.parseInt( str );

		return iRs;

	}

	/**
	 * 대상 int가 0일경우 경우 ""을 return
	 * @param str : 대상 스트링
	 * @return String
	 * @throws Exception
	 */
	public static String intToNull( int iNo ) throws Exception {
		if( iNo == 0 )
			return "";
		else
			return Integer.toString( iNo );
	}

	/**
	 * 대상 double가 0일경우 경우 ""을 return
	 *
	 * @param str : 대상 스트링
	 * @return String
	 * @throws Exception
	 */
	public static String doubleToNull( double iNo ) throws Exception {
		if( iNo == 0 )
			return "";
		else
			return Double.toString( iNo );
	}

	/**
	 * 대상 String 중 특정한 문자가 몇번 들어가 있는지 return
	 *
	 * @param str : 대상 String
	 * @param find : 찾고자 하는 String
	 * @return int
	 * @throws Exception
	 */
	public static int cntInStr( String str, String find ) throws Exception {
		int i = 0;
		int pos = 0;
		while( true ) {
			pos = str.indexOf( find, pos );
			if( pos == -1 ) break;
			i++;
			pos++;
		}

		return i;
	}

	/**
	 * String 중 start위치 부터 count만큼 잘라내서 return 주의점-> start 는 1 base 가 아니고 0 base
	 *
	 * @param str : 대상 문자열
	 * @param start : 시작 index
	 * @param count : 잘라올 character 수
	 * @return String
	 * @throws Exception
	 */
	public static String midString( String str, int start, int count ) throws Exception {
		if( str == null ) return null;

		String result = null;

		if( start >= str.length() )
			result = "";
		else if( str.length() < start + count )
			result = str.substring( start, str.length() );
		else
			result = str.substring( start, start + count );

		return result;
	}

	/**
	 * 대상 String 의 뒤에서부터 count만큼 잘라내서 return
	 *
	 * @param str : 대상 문자열
	 * @param count : 잘라낼 character 수
	 * @return String
	 * @throws Exception
	 */
	public static String rightString( String str, int count ) throws Exception {
		if( str == null ) return null;

		String result = null;

		if( count == 0 ) // 갯수가 0 이면 공백을
			result = "";
		else if( count > str.length() ) // 문자열 길이보다 크면 문자열 전체를
			result = str;
		else
			result = str.substring( str.length() - count, str.length() );
		// 오른쪽 count 만큼 리턴

		return result;
	}

	/**
	 * 대상 String 의 뒤에서부터 count만큼 자르기
	 *
	 * @param str : 대상 문자열
	 * @param count : 잘라낼 character 수
	 * @return String
	 * @throws Exception
	 */
	public static String rightCutString( String str, int count ) throws Exception {
		if( str == null ) return null;

		String result = null;

		if( count == 0 ) // 갯수가 0 이면 공백을
			result = "";
		else if( count > str.length() ) // 문자열 길이보다 크면 문자열 전체를
			result = str;
		else
			result = str.substring( 0, str.length() - count );
		// 오른쪽 count 만큼 리턴

		return result;
	}

	/**
	 * 대상 String 에서 특정 String을 찾아서 다른 String으로 대체하여 return
	 *
	 * @param str : 대상 String
	 * @param from : 찾는 String
	 * @param to : 취환할 String
	 * @return String
	 */
	public static String replace( String str, String from, String to ) {
		String sResult = "";
		try {
			if( str == null || str.length() == 0 || from == null || from.length() == 0
					|| to == null || to.length() == 0 ) return str;

			StringBuffer sb = null;

			sb = new StringBuffer( str.length() * 2 );
			String posString = str.toLowerCase();
			String cmpString = from.toLowerCase();
			int i = 0;
			boolean done = false;
			while( i < str.length() && !done ) {
				int start = posString.indexOf( cmpString, i );
				if( start == -1 ) {
					done = true;
				} else {
					sb.append( str.substring( i, start ) + to );
					i = start + from.length();
				}
			}
			if( i < str.length() ) {
				sb.append( str.substring( i ) );
			}

			sResult = sb.toString();
		} catch( Exception e ) {
			sResult = str;
		} finally {

		}

		return sResult;
	}

	/**
	 * 대상 String 에서 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
	 *
	 * @param sHTML : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String replaceHtmlTag( String sHTML ) throws Exception {
		if( sHTML == null || sHTML.trim().equals( "" ) ) return "";

		String sResult = "";
		StringBuffer sbResult = null;

		try {
			String s = "";
			sbResult = new StringBuffer();

			for( int i = 0; i < sHTML.length(); i++ ) {
				s = sHTML.substring( i, i + 1 );

				if( s.equals( "<" ) ) {
					sbResult.append( "&lt;" );
				} else if( s.equals( ">" ) ) {
					sbResult.append( "&gt;" );
				} else if( s.equals( "\"" ) ) {
					sbResult.append( "&quot;" );
				} else if( s.equals( "'" ) ) {
					sbResult.append( "&#39;" );
				} else if( s.equals( "&" ) ) {
					sbResult.append( "&amp;" );
				} else {
					sbResult.append( s );
				}
			}

			sResult = sbResult.toString();
		} finally {
			sbResult = null;
		}

		return sResult;
	}

	/**
	 * 대상 String 을 trim 처리 한 후 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
	 *
	 * @param sHTML : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String replaceHtmlTagTrim( String sHTML ) throws Exception {
		return replaceHtmlTag( trim( sHTML ) );
	}

	/**
	 * 대상 String 에서 ' 를 &#39; 로 바꿔서 return
	 *
	 * @param str : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String replaceQuote( String str ) throws Exception {
		return nvl( replace( str, "'", " &#39; " ) );
	}
	/**
	 * 대상 String 에서 ' 를 &#39; 로 바꿔서 return
	 *
	 * @param str : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String replaceAnd( String str ) throws Exception {
		return nvl( replace( str, "&", "&amp;" ) );
	}
	/**
	 * 대상 String 에서 " 를 &quot; 로 바꿔서 return
	 *
	 * @param str : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String replaceDQuote( String str ) throws Exception {
		return nvl( replace( str, "\"", " &quot; " ) );
	}

	/**
	 * 대상 String 에서 \r\n 을 <BR>로 바꿔서 return
	 *
	 * @param str : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String newlineToBr( String str ) throws Exception {
		if( str == null || "".equals( str ) ) return "&nbsp;";
		return replace( str, "\r\n", "<BR>" );
	}

	/**
	 * 대상 String 에서 \r\n 을 \\r\\n 으로 바꿔서 return
	 *
	 * @param str : 대상 String
	 * @return String
	 * @throws Exception
	 */
	public static String newlineToChar( String str ) throws Exception {
		if( str == null || "".equals( str ) ) return "&nbsp;";
		return replace( str, "\r\n", "\\r\\n" );
	}

	/**
	 * 대상 String 앞에 공백을 삽입하여 원하는 길이의 String을 return
	 *
	 * @param str : 대상 String
	 * @param len : 원하는 String의 길이
	 * @return String
	 * @throws Exception
	 */
	public static String LeftBlanks( String str, int len ) throws Exception {
		int i;
		StringBuffer buffer = new StringBuffer( str.trim() );
		int buffLen = buffer.length();
		if( buffLen < len ) {
			for( i = buffLen; i < len; i++ ) {
				buffer.insert( 0, " " );
			}
		}

		return buffer.toString();
	}

	/**
	 * 대상 int 를 String으로 변환하여 앞에 0 을 삽입하여 원하는 길이의 String을 return
	 *
	 * @param in : 대상 int
	 * @param len : 원하는 String의 길이
	 * @return String
	 * @throws Exception
	 */
	public static String LeftZeros( int in, int len ) throws Exception {
		String line = Integer.toString( in );
		int i;
		StringBuffer buffer = new StringBuffer( line.trim() );
		int buffLen = buffer.length();
		if( buffLen < len ) {
			for( i = buffLen; i < len; i++ ) {
				buffer.insert( 0, "0" );
			}
		}

		return buffer.toString();
	}

	/**
	 * 대상 String을 int로 변환하여 return
	 *
	 * @param s : 대상 String
	 * @return int
	 */
	public static int toInt( String s ) {
		return Integer.parseInt( s );
	}

	/**
	 * 대상 int를 String 으로 변환하여 return
	 *
	 * @param i : 대상 int
	 * @return String
	 * @throws Exception
	 */
	public static String toString( int i ) {
		return Integer.toString( i );
	}


	/**
	 * 한글 포함한 스트링의 정확한 length 구하는 메소드
	 *
	 * @param s : 한글 포함한 스트링
	 * @return int
	 * @throws Exception
	 */
	public static int getLength( String s ) throws Exception {
		int strlen = 0;

		for( int j = 0; j < s.length(); j++ ) {
			char c = s.charAt( j );
			if( c < 0xac00 || 0xd7a3 < c )
				strlen++;
			else
				strlen += 2; //한글
		}
		return strlen;
	}

	/**
	 * 한글 포함한 스트링의 정확한 byte 구하는 메소드
	 *
	 * @param s : 한글 포함한 스트링
	 * @return int
	 * @throws Exception
	 */
	public static int getByteLength( String s ) throws Exception {
		int strlen = 0;

		for( int j = 0; j < s.length(); j++ ) {
			char c = s.charAt( j );
			if( c  > 0x007F )
				strlen += 2; //한글
			else
				strlen++;

		}
		return strlen;
	}

	/**
	 * 문자가 길경우에 특정 바이트 단위 길이로 자른다
	 *
	 * @param str : 문자열
	 * @param byteSize : 남길 문자열의 길이
	 * @return String : 자르고 남은 문자열
	 * @throws Exception
	 */
	public static String getTitleOfList( String str, int byteSize ) throws Exception {
		int rSize = 0;
		int len = 0;

		if( str.getBytes().length > byteSize ) {
			for( ; rSize < str.length(); rSize++ ) {
				if( str.charAt( rSize ) > 0x007F )
					len += 2;
				else
					len++;

				if( len > byteSize ) break;
			}
			str = str.substring( 0, rSize ) + "...";
		}

		return str;

	}

	/**
	 * 한글 포함한 스트링의 정확한 substring
	 *
	 * @param s : 한글 포함한 스트링
	 * @param begin : begin
	 * @param end : end
	 * @return String : 한글 포함한 substring
	 * @throws Exception
	 */
	public static String getSubString( String s, int begin, int end ) throws Exception {
		int rlen = 0;
		int sbegin = 0;
		int send = 0;

		for( sbegin = 0; sbegin < s.length(); sbegin++ ) {
			if( s.charAt( sbegin ) > 0x007f ) {
				rlen += 2;
				if( rlen > begin ) {
					rlen -= 2;
					break;
				}
			} else {
				rlen++;
				if( rlen > begin ) {
					rlen--;
					break;
				}
			}
		}

		for( send = sbegin; send < s.length(); send++ ) {
			if( s.charAt( send ) > 0x007f ) {
				rlen += 2;
			} else {
				rlen++;
			}

			if( rlen > end ) break;
		}

		return s.substring( sbegin, send );
	}

	/**
	 * 한글 포함한 스트링의 정확한 substring 글자 수 초과시 ... 을 붙임
	 *
	 * @param s : 한글 포함한 스트링
	 * @param begin : begin byte
	 * @param end : end byte
	 * @return String : 한글 포함한 substring
	 * @throws Exception
	 */
	public static String getSubStringAddDot( String s, int begin, int end ) throws Exception {
		int rlen = 0;
		int sbegin = 0;
		int send = 0;
		String returnString = "";

		for( sbegin = 0; sbegin < s.length(); sbegin++ ) {
			if( s.charAt( sbegin ) > 0x007f ) {
				rlen += 2;
				if( rlen > begin ) {
					rlen -= 2;
					break;
				}
			} else {
				rlen++;
				if( rlen > begin ) {
					rlen--;
					break;
				}
			}
		}

		for( send = sbegin; send < s.length(); send++ ) {
			if( s.charAt( send ) > 0x007f ) {
				rlen += 2;
			} else {
				rlen++;
			}

			if( rlen > end )
				break;
		}

		returnString = s.substring( sbegin, send );

		if(rlen > end)
			returnString = returnString + "...";

		return returnString;
	}

	/**
	 * StringTokenizer를 이용해서 원하는 구분자로 대상 String을 잘라서 일차원 String 배열로 리턴
	 *
	 * @param pStr : 대상 String
	 * @param pDelim : 구분자 String
	 * @return String[] : 구분자로 나눠진 String 배열
	 * @throws Exception
	 */
	public static String[] splitString( String pStr, String pDelim ) throws Exception {
		if( pStr == null || pStr.length() == 0 || pDelim == null || pDelim.length() == 0 ) {
			return null;
		}

		String[] results = null;

		Vector vec = null;

		StringTokenizer stz = null;

		try {
			vec = new Vector();

			stz = new StringTokenizer( pStr, pDelim );

			while( stz.hasMoreElements() ) {
				String temp = stz.nextToken();

				vec.addElement( temp );
			}

			results = new String[vec.size()];
			for( int i = 0; i < results.length; i++ ) {
				results[i] = (String)vec.elementAt( i );
			}
		} finally {
			vec = null;
			stz = null;
		}

		return results;
	}

	/**
	 * 대상 String 내에 특정 String 패턴을 HTML태그로 강조
	 *
	 * @param pString : 대상 String
	 * @param pPattern : 강조할 String 패턴
	 * @return String : 강조된 String
	 * @throws Exception
	 */
	public static String getHighLighting( String pString, String pPattern ) throws Exception {
		if( pString == null || pString.length() == 0 || pPattern == null || pPattern.length() == 0 )
			return pString;

		String[] somePatterns = null;
		try {
			somePatterns = splitString( pPattern, " " );

			for( int i = 0; i < somePatterns.length; i++ ) {
				//System.out.println(somePatterns[i]);
				pString = replace( pString, somePatterns[i], "<FONT COLOR=\"red\">"
						+ somePatterns[i] + "</FONT>" );
			}
		} finally {
			somePatterns = null;
		}

		return pString;
	}

	/**
	 * 제목을 보여줄때 제한된 길이를 초과하면 뒷부분을 짜르고 "..." 으로 대치한다.
	 *
	 * @param str : 대상 String
	 * @param len : 나타낼 길이
	 * @return String : ...추가된 String
	 * @throws Exception
	 */
	public static String formatTitle( String strTarget, int iLimit ) throws Exception {


		if(strTarget == null || strTarget.equals("")) return "";

		int rSize = 0;
		int len = 0;

		if( strTarget.getBytes().length > iLimit ) {
			for( ; rSize < strTarget.length(); rSize++ ) {
				if( strTarget.charAt( rSize ) > 0x007F )
					len += 2;
				else
					len++;

				if( len > iLimit ) break;
			}
			strTarget = strTarget.substring( 0, rSize ) + "...";
		}

		return strTarget;
	}

	/**
	 * 태그 제거하기
	 *
	 * 사용 예: String str = "<BODY>Hello <FONT FACE=\"궁서\"><B>진\"하게\"</B> 쓰>기</FONT></BODY>";
	 * System.out.println(removeTag(str));
	 *
	 * 결과: 진\"하게\" 쓰>기
	 *
	 * @param s : tag포함된 문장
	 * @return String : tag제거된 문장
	 * @throws Exception
	 */
	public static String getRemoveTag( String s ) throws Exception {

		if(s == null) return "";

		final int iNormalState = 0;
		final int iTagState = 1;
		final int iStartTagState = 2;
		final int iEndiTagState = 3;
		final int iSingleQuotState = 4;
		final int iDoubleQuotState = 5;
		int state = iNormalState;
		int oldState = iNormalState;
		char[] chars = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		char a;
		for( int i = 0; i < chars.length; i++ ) {
			a = chars[i];
			switch( state ) {
			case iNormalState :
				if( a == '<' )
					state = iTagState;
				else
					sb.append( a );
				break;
			case iTagState :
				if( a == '>' )
					state = iNormalState;
				else if( a == '\"' ) {
					oldState = state;
					state = iDoubleQuotState;
				} else if( a == '\'' ) {
					oldState = state;
					state = iSingleQuotState;
				} else if( a == '/' )
					state = iEndiTagState;
				else if( a != ' ' && a != '\t' && a != '\n' && a != '\r' && a != '\f' )
					state = iStartTagState;
				break;
			case iStartTagState :
			case iEndiTagState :
				if( a == '>' )
					state = iNormalState;
				else if( a == '\"' ) {
					oldState = state;
					state = iDoubleQuotState;
				} else if( a == '\'' ) {
					oldState = state;
					state = iSingleQuotState;
				} else if( a == '\"' )
					state = iDoubleQuotState;
				else if( a == '\'' ) state = iSingleQuotState;
				break;
			case iDoubleQuotState :
				if( a == '\"' ) state = oldState;
				break;
			case iSingleQuotState :
				if( a == '\'' ) state = oldState;
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 도메인에서 HTTP 자르기
	 *
	 * @param in
	 * @return String : http제거된 url
	 */
	public static String cutHTTP( String in ) {
		if( in == null ) return null;
		if( in.length() < 4 ) return in;

		if( in.substring( 0, 7 ).equals( "http://" ) ) {
			in = in.substring( 7 );
			in = in.substring( in.indexOf( "/" ), in.length() );
		}
		return in;
	}

	/**
	 * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
	 *
	 * @param input
	 * @return String
	 */
	public static String Split1( String input ) {
		// null체크
		if( input == null ) {
			return null;
		}
		if( input.equals( "" ) ) {
			return null;
		}

		String result = new String();
		int part = 0;

		part = input.indexOf( ":" );
		result = input.substring( 0, part );
		return result;
	}

	/**
	 * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
	 *
	 * @param input
	 * @return String
	 */
	public static String Split2( String input ) {
		// null체크
		if( input == null ) {
			return null;
		}
		if( input.equals( "" ) ) {
			return null;
		}

		String result = new String();
		int part = 0, all = 0;

		part = input.indexOf( ":" );
		all = input.length();
		result = input.substring( part + 1, all );

		return result;
	}

	/**
	 * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
	 * @param input
	 * @return String[]
	 */
	public static String[] Split( String input ) {
		// null체크
		if( input == null ) {
			return null;
		}
		if( input.equals( "" ) ) {
			return null;
		}

		String[] result = new String[5];
		int part = 0;

		for( int i = 0; i < 4; i++ ) {
			part = input.indexOf( ":" );
			result[i] = input.substring( 0, part );
			input = input.substring( part + 1 );
		}
		result[4] = input;
		return result;
	}

	/**
	 * 년,월,일,시,분등과 관련된 HTML <option> 을 출력한다. default 값을 주면 그 값이 선택되게 한다.
	 *
	 * @param start
	 * @param end
	 * @param nDefault
	 * @return String
	 */
	public static String getDateOptions( int start, int end, int nDefault ) {
		String result = "";

		for( int i = start; i <= end; i++ ) {
			if( i < 100 ) {
				String temp = String.valueOf( i + 100 );

				temp = temp.substring( 1 );

				if( i == nDefault ) {
					result += "<option value=\"" + temp + "\" selected>" + temp;
				} else {
					result += "<option value=\"" + temp + "\">" + temp;
				}
			} else {
				if( i == nDefault ) {
					result += "<option value=\"" + i + "\" selected>" + i;
				} else {
					result += "<option value=\"" + i + "\">" + i;
				}
			}
		}
		return result;
	}

	/**
	 * 숫자값을 콤마 처리 후 결과값 리턴 - 숫자형 Argement
	 *
	 * @param ps_val
	 * @return String
	 */
	public static String setComma( String ps_val ) {
		long l_val = 0;
		if( ( ps_val == null ) || ( ps_val.length() == 0 ) ) return "0";

		l_val = Long.parseLong( ps_val );
		java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
		String s_tmp = s_fmt.format( l_val );
		return s_tmp;
	}

	/**
	 * 숫자값을 콤마 처리 후 결과값 리턴 - Long형 Argement
	 *
	 * @param pl_val
	 * @return String
	 */
	public static String setComma( long pl_val ) {
		java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
		String s_tmp = s_fmt.format( pl_val );
		return s_tmp;
	}

	/**
	 * 숫자값을 콤마 처리 후 결과값 리턴 - double Argement
	 *
	 * @param pl_val
	 * @return String
	 */
	public static String setComma( double pl_val ) {
		java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
		String s_tmp = s_fmt.format( pl_val );
		return s_tmp;
	}

	/**
	 * subMoney
	 *
	 * @param pl_val
	 * @return String
	 */
	public static String subMoney( long pl_val ) {
		String sl_val = "";
		sl_val = Long.toString( pl_val );

		if( !"".equals( sl_val ) ) {
			if( sl_val.length() > 3 ) {
				sl_val = sl_val.substring( 0, sl_val.length() - 3 );
			}
		}

		java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
		String s_tmp = s_fmt.format( Long.parseLong( sl_val ) );
		return s_tmp;
	}

	/**
	 * subMoney
	 *
	 * @param pl_val
	 * @return String
	 */
	public static String subMoney( double pl_val ) {
		String sl_val = "";
		sl_val = Double.toString( pl_val );

		if( !"".equals( sl_val ) ) {
			if( sl_val.length() > 3 ) {
				sl_val = sl_val.substring( 0, sl_val.length() - 3 );
			}
		}

		java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
		String s_tmp = s_fmt.format( Long.parseLong( sl_val ) );
		return s_tmp;
	}

	/**
	 * 문자열에서 콤마제거 후 결과값 리턴
	 *
	 * @param ps_val
	 * @return String
	 */
	public static String getDeleteComma( String ps_val ) {
		StringTokenizer s_tok_str = new StringTokenizer( ps_val, "," );
		String s_con_str = "";
		while( s_tok_str.hasMoreTokens() ) {
			String s_tmp = s_tok_str.nextToken();
			s_con_str = s_con_str + s_tmp;
		}
		return s_con_str.trim();
	}

	/**
	 * 문자열에서 특정문자 제거후 리턴
	 *
	 * @param ps_val
	 * @param chrs
	 * @return String
	 */
	public static String getDeleteChar( String ps_val, String chrs ) {
		StringTokenizer s_tok_str = new StringTokenizer( ps_val, chrs );
		String s_con_str = "";
		while( s_tok_str.hasMoreTokens() ) {
			String s_tmp = s_tok_str.nextToken();
			s_con_str = s_con_str + s_tmp;
		}
		return s_con_str.trim();
	}

	/**
	 * formatMoney
	 *
	 * @param str
	 * @return String
	 */
	public static String formatMoney( String str ) {
		if( str == null || "".equals( str ) ) {

		} else {
			double iAmount = ( new Double( str ) ).doubleValue();
			java.text.DecimalFormat df = new java.text.DecimalFormat( "###,###,###,###,###,###,###" );
			return df.format( iAmount );
		}

		return "0";
	}

	/**
	 * 문자열을 특정 크기로 만듬, 만약 남는 공간이 있으면 왼쪽에서부터 특정문자(cSpace)를 채움
	 * null이 입력되더라도 크기 만큼 특정문자를 채움
	 *
	 * @param strText : String 문자열
	 * @param cSpace : char 빈공란에 채울 특정문자
	 * @param iTotalSize : int 특정 크기
	 * @param bIsLeft : 왼쪽채우기
	 * @return Stirng : 변경된 문자열
	 */
	public static String fixTextSize( String strText, char cSpace, int iTotalSize, boolean bIsLeft ) {

		if( strText == null ) {
			strText = "";
		}

		if( strText.length() < iTotalSize ) {

			// 문자열의 크기가 특정크기보다 작을 때는 특정문자로 채움
			char[] carraySpace = new char[iTotalSize - strText.length()];
			Arrays.fill( carraySpace, cSpace );

			String strSpace = new String( carraySpace );

			if( bIsLeft ) {
				// 왼쪽으로 공백을 채움
				return strSpace + strText;
			} else {
				// 오른쪽으로 공백을 채움
				return strText + strSpace;
			}

		} else {

			// 문자열의 크기가 특정크기보다 클때는 앞쪽의 문자열 잘라냄
			return strText.substring( strText.length() - iTotalSize, strText.length() );

		}

	}

	public static String printRegisterNo( String registno ) {
		String rtnStr = "";
		if( registno.length() >= 6 ) {
			rtnStr = registno.substring( 0, 6 ) + "-XXXXXXX";
		}
		return rtnStr;
	}

	/**
	 * 데이타베이스에 저장하기전 문자열 치환
	 *
	 * @param srcStr : 데이타베이스에 저장하기전 변경할 문자열
	 * @return String : 치환된 문자열
	 */
	public static String putReplaceStr( String srcStr ) {
		if( srcStr != null ) {
			srcStr = srcStr.replace( '\'', '' );
			srcStr = srcStr.replace( ',', '' );
		}
		return srcStr;
	}


	/**
	 * 문자열 치환하기 양수용 추가
	 *
	 * @param src : String
	 * @param oldstr : String
	 * @param newstr : String
	 * @return String
	 * @throws Exception
	 */
	public static String str_replace( String src, String oldstr, String newstr ) throws Exception {
		return str_replace( src, oldstr, newstr, false );
	}

	/**
	 * 대소문자 상관없이 문자열 치환하기 양수용 추가
	 *
	 * @param src : String
	 * @param oldstr : String
	 * @param newstr : String
	 * @param ignorecase : boolean
	 * @return String
	 * @throws Exception
	 */
	public static String str_replace( String src, String oldstr, String newstr, boolean ignorecase )
			throws Exception {
		return str_replace( src, oldstr, newstr, ignorecase, false );
	}

	/**
	 * 대소문자 상관없이 문자열 치환하기 양수용 추가
	 *
	 * @param src : String
	 * @param oldstr : String
	 * @param newstr : String
	 * @param ignorecase : boolean
	 * @return String
	 * @throws Exception
	 */
	public static String str_replace( String src, String oldstr, String newstr, boolean ignorecase,
			boolean firstonly ) throws Exception {
		if( src == null ) return null;

		StringBuffer dest = new StringBuffer( "" );
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;

		if( ignorecase ) {
			while( ( pos = indexOfi( src, oldstr, oldpos ) ) >= 0 ) {
				dest.append( src.substring( oldpos, pos ) );
				dest.append( newstr );
				oldpos = pos + len;
				if( firstonly ) break;
			}
		} else {
			while( ( pos = src.indexOf( oldstr, oldpos ) ) >= 0 ) {
				dest.append( src.substring( oldpos, pos ) );
				dest.append( newstr );
				oldpos = pos + len;
				if( firstonly ) break;
			}
		}

		if( oldpos < srclen ) dest.append( src.substring( oldpos, srclen ) );

		return dest.toString();
	}

	/**
	 * 대소문자 상관없이 문자열 검색하기 양수용 추가
	 * @param src : String
	 * @param find : String
	 * @param pos : int
	 * @return int
	 * @throws Exception
	 */
	public static int indexOfi( String src, String find, int pos ) throws Exception { // 대소문자 상관안하고 스트링위치를 찾는 함수
		find = find.toLowerCase(); // 전부 소문자로..
		int src_len = src.length();
		int find_len = find.length();
		int src_pos = pos;
		int find_pos = 0;

		while( find_pos < find_len && src_pos < src_len ) {
			char src_char = src.charAt( src_pos );
			if( ( src_char >= 'A' && src_char <= 'Z' ) || ( src_char >= 'a' && src_char <= 'z' ) ) { // 알파벳 대역이면,
				if( src_char == find.charAt( find_pos ) || src_char == find.charAt( find_pos ) - 32 )
					find_pos++;
				else
					find_pos = 0;
			} else {
				if( src_char == find.charAt( find_pos ) )
					find_pos++;
				else
					find_pos = 0;
			}
			src_pos++;
		}

		if( find_pos == find_len )
			return src_pos - find_len;
		else
			return -1;
	}

	/**********************************************************************************/

	/**
	 * 태그 인젝션 처리후 반환
	 *
	 * @param value
	 * @param bContent
	 * @return String
	 */
	public static String getTagInjection(String value, boolean bContent)
	{
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");

		if(bContent) {
			value = value.replaceAll("&lt;p&gt;", "<p>");
			value = value.replaceAll("&lt;br&gt;", "<br>");
		}

		return value;
	}

	/**
	 * sql 비유효성 문자 제거
	 *
	 * @param value
	 * @return String
	 */
	public static String getSqlInjection(String value) {
		if(value.indexOf(";") > 0) {
			value = getDeleteChar(value, ";");
		}

		if(value.indexOf(",") > 0) {
			value = getDeleteChar(value, ",");
		}

		if(value.indexOf("\'") > 0) {
			value = getDeleteChar(value, "\'");
		}

		if(value.indexOf("\"") > 0) {
			value = getDeleteChar(value, "\"");
		}

		if(value.indexOf("+") > 0) {
			value = getDeleteChar(value, "+");
		}

		return value;
	}

	/**
	 * 파일명의 확장자를 검사하여 업로드 가능여부를 반환
	 *
	 * @param FileName
	 * @return boolean
	 */
	public static boolean IsAbleUploadFile(String FileName) {
		boolean result = true;

		String file_ext = FileName.substring(FileName.lastIndexOf('.') + 1);

		//업로드 금지 파일
		if(!( file_ext.equalsIgnoreCase("hwp") ||
				file_ext.equalsIgnoreCase("pdf") ||
				file_ext.equalsIgnoreCase("jpg")) ) {
				result = false;
		}

		return result;
	}

	/**
	 * 의도적인 deserialization 를 금지하기 위해..클래스 내에 아래 메소드를 추가한다
	 *
	 * private final void readObject(ObjectInputStream in)
	 * 	throws java.io.IOException {
	 *  throw new java.io.IOException("Class cannot be deserialized");
	 *
	 *@param pageNumber, rowCount
	 * @return int[]
	 */
	public static int[] getStartEndIndex(int pageNumber, int rowCount) {

		int index[] = new int[2];
		index[0] = rowCount * (pageNumber - 1) + 1;
		index[1] = rowCount * pageNumber;

		if (index[0] < 0)
			index[0] = 0;
		if (index[1] < 0)
			index[1] = 0;

		return index;
	}

	/**
	 * 정수 7의 문자를 07, 007의 문자열로 치환
	 * ex) zeroFill("000", 7)  ===> 007
	 *
	 * @param format
	 * @param input
	 * @return String
	 */
	public static String zeroFill(String format, String input){
		DecimalFormat DF = new DecimalFormat(format);
		String outPut = DF.format(Integer.parseInt(input));
		return outPut;
	}

	public static String getImageTag(String content)
	{
		String contents = "";
		String imgSrcTag = "";
		String strTmp1 = "";
		String strTmp2 = "";
		int intTemp1 = 0;

		//contents = content.toUpperCase();
		contents = content;

		intTemp1 = contents.indexOf("<IMG");

		if(intTemp1 <= 5)
			intTemp1 = contents.indexOf("<img");

		if(intTemp1 > 5)
		{
			strTmp1 = contents.substring(intTemp1);
			strTmp2 = strTmp1.substring(strTmp1.indexOf("src="), strTmp1.indexOf(">")).replace("src=\"", "");
			imgSrcTag = strTmp2.substring(0, strTmp2.indexOf('"'));
		}
		else
		{
			imgSrcTag = "N";
		}
		return imgSrcTag;
	}


	//XSS 처리
	public static String cleanXSS(String str)
    {
		str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		str = str.replaceAll("eval\\((.*)\\)", "");
		return str;
    }

	//HTML태그
	public static String replaceTagtoHtml(String str){
		if(str != null){
			str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
			str = str.replaceAll("&&#40;", "&#40;").replaceAll("&&#41;", "&#41;");
		}

		return str;
	}

	//replaceInvaild
	public static String replaceInvaild(String str) {

		if(str != null){
			//XSS 체크
			str = cleanXSS(str);
			return str;
		}else{
			return str;
		}
	}

	public static String splitCellphoneNum(String phoneNum)
	{
		if(!chkNum(phoneNum))
			return phoneNum;


		HashMap map = new HashMap();
		String resultPhoneNum = "";

		if(phoneNum.length() < 10)
			return phoneNum;

		map = splitPhoneNum(phoneNum, "S");

		resultPhoneNum = map.get("hd_1").toString() + "-" + map.get("hd_2").toString() + "-" + map.get("hd_3").toString();

		return resultPhoneNum;
	}

	/**
	 * 폰번호 나누기
	 *
	 * @param phoneNum
	 * @param type : S(휴대폰), H(일반전화)
	 * @param response
	 * @return HashMap
	 */
	public static HashMap splitPhoneNum(String phoneNum, String type)
	{
		HashMap resultHd = new HashMap();

		if(phoneNum == null || phoneNum.equals(""))
		{
			return resultHd;
		}

		int pnLangth = phoneNum.length();

		if(type.equals("S"))	// 휴대폰
		{
			resultHd.put("hd_1", phoneNum.substring(0, 3));

			if(pnLangth == 10)
			{
				resultHd.put("hd_2", phoneNum.substring(3, 6));
				resultHd.put("hd_3", phoneNum.substring(6, 10));
			}
			else if (pnLangth == 11)
			{
				resultHd.put("hd_2", phoneNum.substring(3, 7));
				resultHd.put("hd_3", phoneNum.substring(7, 11));
			}

		}
		else	// 일반전화
		{
			if(phoneNum.substring(0, 2).equals("02"))	// 서울지역
			{
				resultHd.put("hd_1", phoneNum.substring(0, 2));

				if(pnLangth == 9)
				{
					resultHd.put("hd_2", phoneNum.substring(2, 5));
					resultHd.put("hd_3", phoneNum.substring(5, 9));
				}
				else if (pnLangth == 10)
				{
					resultHd.put("hd_2", phoneNum.substring(2, 6));
					resultHd.put("hd_3", phoneNum.substring(6, 10));
				}
			}
			else	// 그 외 지역
			{
				resultHd.put("hd_1", phoneNum.substring(0, 3));

				if(pnLangth == 10)
				{
					resultHd.put("hd_2", phoneNum.substring(3, 6));
					resultHd.put("hd_3", phoneNum.substring(6, 10));
				}
				else if (pnLangth == 11)
				{
					resultHd.put("hd_2", phoneNum.substring(3, 7));
					resultHd.put("hd_3", phoneNum.substring(7, 11));
				}
			}
		}

		return resultHd;
	}

	public static String maskPhoneNumber(String num, Boolean isMask) {
		String frmtNum = "";
		if(nvl(num).equals(""))
			return frmtNum;
		if(num.length() == 11) {
			if(isMask) {
				frmtNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
			} else {
				frmtNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
			}
		} else if(num.length() == 8) {
			frmtNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
		} else {
			if(num.indexOf("02") == 0) {
				if(isMask) {
					frmtNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
				} else {
					frmtNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			} else {
				if(isMask) {
					frmtNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
				} else {
					frmtNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			}
		}
		return frmtNum;
	}


	public static byte[] adjustLimitSizeChar2(String str, int amount) throws java.io.UnsupportedEncodingException
	{
		String trail = " ";
		if (str == null) return "".getBytes();
		String tmp = str;
		int slen = 0, blen = 0;
		char c;

		if( tmp.getBytes("Cp970").length > amount )
		{
			while(blen < amount)
			{
				c = tmp.charAt(slen);
				blen++;
				slen++;

				if(c  > 127) blen++;

				//80byte 체크
				if(blen > amount)
				{
					blen--;
					slen--;

					if (c > 127) blen--;
					break;
				}
			}

			tmp=tmp.substring(0,slen);
			if( tmp.getBytes().length != amount )
			tmp += trail;
		}

		while( tmp.getBytes().length < amount )
		{
			tmp += trail;
		}

		return tmp.getBytes();
	}

	public static String getChangeSpaceValue(String val)
	{
		String result = null;

		if(val != null)
		{
			result = val.replaceAll("<", " ");
			result = result.replaceAll(">", " ");
			result = result.replaceAll("\r\n", " ");
			result = result.replaceAll("\n", " ");
		}

		return result;
	}

	/*
	 * public static String setURLDecode(String value) { String result = null;
	 * 
	 * try { if(value != null) { value = value.trim(); String filePath =
	 * value.substring( 0, value.lastIndexOf("/")+1 ); String fileName =
	 * value.substring( value.lastIndexOf("/")+1, value.length() ); fileName =
	 * EncodeUtil.URLDecode(fileName, "euc-kr");
	 * 
	 * result = filePath + fileName; } } catch(Exception e) { } finally {}
	 * 
	 * 
	 * return result; }
	 */
    public Integer StringToInt(String value) {
    	return Integer.valueOf(value);
    }

	/**
	 * 번호 찢기
	 * @param String num
	 *
	 * @return ModelAndView
	 */
	public static HashMap splitString2(String word, String type)
	{
		HashMap resultWord = new HashMap();
		String arrTempNum [] = null;

		if(!checkNull(word).equals(""))
		{

			arrTempNum = word.split(type);

			for(int i = 0; i < arrTempNum.length; i++)
			{
				resultWord.put(i, arrTempNum[i]);
			}
		}

		return resultWord;
	}

	/**
	 * 변환
	 * @param sc
	 * @return String
	 */
	public static String setString(String sc){
		String changeStr = "";
		changeStr = sc.replaceAll("-", "");

		return changeStr;
	}
	/**

	 * 나이체크(14세, 19세)
	 *
	 * @param request, response, commonDto
	 * @return ModelAndView
	 */
	public static String ageCheck(String birth, Integer age) {


		String returnResult = "";

		String birthday = birth;
		birthday = birthday.replace("-", "");

		int checkAge = age;

		Calendar today = Calendar.getInstance();
		SimpleDateFormat strYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat strMonth = new SimpleDateFormat("MM");
		SimpleDateFormat strDay = new SimpleDateFormat("dd");

		String year = Integer.toString(Integer.parseInt(strYear.format(today.getTime())) - checkAge);
		String month = strMonth.format(today.getTime());
		String day = strDay.format(today.getTime());

		String age2 = year + month + day;

		if(Integer.parseInt(birthday) > Integer.parseInt(age2)) {
			returnResult = "false";
		}else{
			returnResult = "true";
		}


		return returnResult;
	}

	/***
	 * 입출력 content Check
	 * @param num
	 * @return boolean
	 */
	public static String changeBoardDocOrderString(String str){

		if (checkNull(str).equals(""))
			return null;

		String returnStr = "";

		if (str.equals("new"))
			returnStr = "P.product_no DESC";
		else if (str.equals("send"))
			returnStr = "P.SEND_CNT DESC";
		else if (str.equals("priceHigh"))
			returnStr = "P.UNIT_PRICE DESC";
		else if (str.equals("priceRow"))
			returnStr = "P.UNIT_PRICE ASC";
		else
			returnStr = "P.READ_CNT DESC";

		return returnStr;
	}

	public static String getStackTraceAsString(Throwable e){
		if(e == null){
			return "Exception is null";
		}
		ByteArrayOutputStream ostr = new ByteArrayOutputStream();
		return ostr.toString();
	}

	//unescape
	public static String unescape(String inp) {
	     StringBuffer rtnStr = new StringBuffer();
	     char [] arrInp = inp.toCharArray();
	     int i;
	     for(i=0;i<arrInp.length;i++) {
	         if(arrInp[i] == '%') {
	             String hex;
	             if(arrInp[i+1] == 'u') {    //유니코드.
	                 hex = inp.substring(i+2, i+6);
	                 i += 5;
	             } else {    //ascii
	                 hex = inp.substring(i+1, i+3);
	                 i += 2;
	             }
	             try{
	                 rtnStr.append(Character.toChars(Integer.parseInt(hex, 16)));
	             } catch(NumberFormatException e) {
	              rtnStr.append("%");
	                 i -= (hex.length()>2 ? 5 : 2);
	             }
	         } else {
	          rtnStr.append(arrInp[i]);
	         }
	     }

	     return rtnStr.toString();
	 }

	 /** 스트링이 빈스트링이나 null이면 true */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals(""))
            return true;
        else
            return false;
    }

    public static String unescapeXML(String str) {
        if (str == null || str.length() == 0)
            return "";

        StringBuffer buf = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (c == '&') {
                int pos = str.indexOf(";", i);
                if (pos == -1) { // Really evil
                    buf.append('&');
                } else if (str.charAt(i + 1) == '#') {
                    int val = Integer.parseInt(str.substring(i + 2, pos), 16);
                    buf.append((char) val);
                    i = pos;
                } else {
                    String substr = str.substring(i, pos + 1);
                    if (substr.equals("&amp;"))
                        buf.append('&');
                    else if (substr.equals("&lt;"))
                        buf.append('<');
                    else if (substr.equals("&gt;"))
                        buf.append('>');
                    else if (substr.equals("&quot;"))
                        buf.append('"');
                    else if (substr.equals("&apos;"))
                        buf.append('\'');
                    else if (substr.equals("&nbsp;"))
                        buf.append(' ');
                    else
                        // ????
                        buf.append(substr);
                    i = pos;
                }
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String htmlToText(String html) {
        if (html == null)
            return null;
        String text = unescapeXML(html);

        text.replaceAll("((<|</)\\<\\/?(br|BR)[^\\>]*\\>>)", "\n");
        text.replaceAll("<[^>]*>", "");
        text.replaceAll("(  |\t|\t\n\t| \n|\n |\n\n)", "");

        return text;
    }

    public static String byteCut(String msg, int cutSize) {
		msg = msg.replaceAll("\r", "");
		String str = "";
		//System.out.println("msg:"+msg);
		if(msg.length() > 0){
			str = limitMsg(msg, cutSize);
		}

		return str;
	}

    public static Collection byteCutCol(String msg, int cutSize) {
		msg = msg.replaceAll("\r", "");
		String str = "";
		//System.out.println("msg:"+msg);
		Collection msgCol = new ArrayList();
		for(int k=0; k < 4; k++){
			if(msg.length() > 0){
				str = limitMsg(msg, cutSize);
				msgCol.add(str);
				msg = msg.substring(str.length());
			}
		}

		return msgCol;
	}

    public static String limitMsg(String msg, int cutSize) {
		int bytes=0;
		for(int k=0; k < msg.length(); k++){
			bytes += String.valueOf(msg.charAt(k)).getBytes().length;
			if(bytes > 80){
				msg = msg.substring(0, k);
				break;
			}
		}
		//System.out.println("msg2:"+msg);
		return msg;
	}

    public static String getChangedUtubeUrl(String url)
    {
		try
		{
			if((StringUtil.cntInStr(url, "iframe") > 0))
			{
				int startIndex = url.indexOf("src=");
				int lastIndex = url.indexOf("frameborder");
				//System.out.println(url.substring(startIndex,lastIndex ));
				url = url.substring(startIndex,lastIndex );
				return "<iframe "+url+" frameborder=\"0\" allowfullscreen=\"false\" width=\"362\" height=\"173\"></iframe>";
			}
			if(!(StringUtil.cntInStr(url, "http://www.youtube.com/embed/") > 0))
			{//http://youtu.be/2C3Bm7PcNXI ,http://www.youtube.com/watch?v=2C3Bm7PcNXI
				String utubeCode = "";
				if(StringUtil.cntInStr(url, "http://youtu.be") > 0)
				{
					int startIndex = url.lastIndexOf("/");
					utubeCode = url.substring(startIndex+1,url.length());
					url = "http://www.youtube.com/embed/" + utubeCode;
				}else if(StringUtil.cntInStr(url, "http://www.youtube.com/watch") > 0)
				{
					int startIndex = url.lastIndexOf("=");
					utubeCode = url.substring(startIndex+1,url.length());
					url = "http://www.youtube.com/embed/" + utubeCode;
				}
			}
		}
		catch (Exception e) {
		} finally {}
		return "<iframe src=\""+url+"\" frameborder=\"0\" allowfullscreen=\"false\" width=\"362\" height=\"173\"></iframe>";
    }

    /**
    * <p>XXXXXXXXXX 형식의 10자리 사업자번호 3개를 입력 받아 유효한 사업자번호인지 검사.</p>
    *
    *
    * @param  10자리 사업자번호 문자열
    * @return  XXX - XX - XXXXX 형식의 사업자번호
    */
   public static String setCompNumber(String comp) {
       String temp = checkNull(comp);

       if(temp.length() != 10) return comp;

       return temp.substring(0, 3) + "-" + temp.substring(3, 5) + "-" + temp.substring(5, 10);
   }

	/*
	 * public static String convertGridCombo(List<EgovMap> list, String[] opt,
	 * String firstTxt){ String strCode = !"".equals(firstTxt) ? ":" + firstTxt +
	 * ";" : ""; for(EgovMap rs : list){
	 * 
	 * strCode += rs.get(opt[0]) + ":" + rs.get(opt[1]) + ";"; }
	 * 
	 * strCode = strCode.length() > 0 ? strCode.substring(0, strCode.length()-1) :
	 * "";
	 * 
	 * return strCode; }
	 */
}
