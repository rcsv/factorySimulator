# factory Simulator
<<<<<<< HEAD
"Production Line Simulator",  改め「工場シミュレータ」は、仮想的に工場を表現し、どのような動きをするのかを見て「愛でる」ことが目的となるアプリケーションです。Java 1.7 以降で動作します。考えられる用途として、愛でる他に工場に IT システムを導入するに当って、工場とはどういうところなのかの理解を助けるため、程度のものだと考えてください。
=======
Production Line Simulator 改め「工場シミュレータ」は、仮想的に工場を表現し、どのような動きをするのかを見て愛でる目的のアプリケーションです。Java 1.7 以降で動作します。考えられる用途として、愛でる他に工場に IT システムを導入するに当って、工場とはどういうところなのかの理解を助けるため、程度のものだと考えてください。
>>>>>>> e7bf35664b37e885921c42ed8a4a09b4ec84a460

シミュレータはあくまでシミュレータでそれ以上でもそれ以下でもありません。ゲームでもなければユーティリティでも無い、ちょっと珍しいタイプのプログラムです。

## System configuration
呼び出すクラスから <code>import org.rcsvp.factory.* ;</code> を宣言し、下記の構成で工場を構築してください。

> ###  Factory
> 工場を表現しています。register メソッドを使って「生産ライン」と「従業員」の２種類が登録できます。
>> ### Labor
>> 従業員の事です。名前はリファクタリングで今後 Labor をやめる予定です。従業員は集中管理コンソールを確認し、0 秒距離にある仮想生産ラインへ、壊れたツールを直したり、足りなくなった材料棚に材料を補充したりといった行動を起こします。
>> ### ProductionLine
>> 生産ラインを表現しています。register メソッドを使って「工程」を登録する事が出来ます。初期設定の段階では、その日の製品をくみ上げなければならない数、すなわちノルマと、その生産ラインが持つタクトタイムを設定します。タクトタイムについては別途ぐぐってください。
>>> ### Procedure
>>> 生産ラインを構成する製造工程ひとつひとつを表現しています。register メソッドを使って「在庫棚」「消耗品」「検証項目」を登録する事が出来ます。この製造工程は登録された製造ラインが指定したタクトタイムで全て動作します。各工程は在庫棚、または前工程から材料を受け取り、自らの工程で任意の時間を消費して構築していきます。消耗品は必ず一つの材料につき一度ずつ使用します。工程内部の作業が完了したら、登録した検証項目の検証作業に入ります。検証項目にひっかかった加工品目は「不良品」と見なされ、次の工程にはまわされません。不良品置き場へと排出されていきます。（現段階ではその不良品置き場にたまった材料は誰もケアせず、山積みになっていく仕様です。）完了品へと進化したものについては次工程が受け取れる状態へ移行し、完了します。
<<<<<<< HEAD
>>>> ### Shelf
>>>> 在庫棚を表現しています。在庫棚には場所の制約上、配備数に限界があり早々その日の分全てをマガジン、スロットに配置させるのは困難であるという思想が入っているため、初期設定の情報には「在庫棚の大きさ」を表現する事が出来る様になっています。工程に登録する事が出来ますが、同じインスタンスを複数の工程に登録すると動作がおかしくなりますが、現段階では抑制していません。
>>>> ### Disposable
>>>> 製造工程ひとつひとつで処理する加工項目において、伸び、曲げ、穴あけといった製造工程の治具側に損耗率や摩耗率といった係数が必要になる道具が存在する場合この IDisposable 実装を使用します。
>>>> ### Verification
>>>> 「検証項目」を表現しています。製造工程で任意の加工が成された後、実際にその加工が意図した状態になっているかどうかについて検証します。後述する「公差」に基づいて各項目を実測し、最終的に次工程へ移行できるかどうかを判別します。
>>>>> ### Tolerance
>>>>> 検証項目に対しての「公差」を表現しています。検証内容は寸法的な側面の Scantling, 軸のズレといった幾何的な側面の Geometrical、電気抵抗値といった側面の Electrical の３方面からの公差が用意されています。

各項目を設定した後、工場をスレッドで包んでマルチスレッドとして開始するとシミュレーターの一日が始まります。

> new Thread ( factory ).start() ;

## 今後の予定
シミュレータが開始するまでの工場構築までに中々時間がかかるため、設定ファイル方式構築をしやすくする予定。
=======
>>>> Shelf
>>>> 在庫棚を表現しています。在庫棚には場所の制約上、配備数に限界があり早々その日の分全てをマガジン、スロットに配置させるのは困難であるという思想が入っているため、初期設定の情報には「在庫棚の大きさ」を表現する事が出来る様になっています。工程に登録する事が出来ますが、同じインスタンスを複数の工程に登録すると動作がおかしくなりますが、現段階では抑制していません。
>>>> Disposable
>>>> 製造工程ひとつひとつで処理する加工項目において、伸び、曲げ、穴あけといった製造工程の治具側に損耗率や摩耗率といった係数が必要になる道具が存在する場合この IDisposable 実装を使用します。
>>>> Verification
>>>> 
>>>>> Tolerance

>>>>>>> e7bf35664b37e885921c42ed8a4a09b4ec84a460
## Changelog
- v1.0: Initial release
