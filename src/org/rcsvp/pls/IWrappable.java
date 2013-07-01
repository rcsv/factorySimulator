package org.rcsvp.pls;

/**
 * IWrappable インターフェイスは、今回の工場、ライン、工程ような、
 * リーフ、あるいはノードとして登録できるあらゆるものに対して適用するインターフェイスです。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IWrappable {

	void updateName ( String givenName );

	void register ( IWrappable childNode );

}