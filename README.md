## profile_site_app
シングルページアプリケーション（SPA）のプロフィール管理アプリの基幹サービス。  

### 概要
* コンテンツとしてプロフィール、経歴、記事等を扱う。
* エンドユーザはURLでコンテンツにアクセスする。
* コンテンツはツリー状のデータ構造で管理する。
* ツリーの各ノードがURLの階層と関連付いている。
* ツリーの末端ノード(葉)をコンテンツとして扱う。

### アプリ構成
 * 基幹サービス(__本プロジェクト__）
     コンテンツ管理機能とWebAPIを提供するWebサーバ。
 * クライアントサービス  
     SPA画面を提供するクライアントサーバ。
 * バッチ
     コンテンツの集計バッチ。(作成優先度低)
