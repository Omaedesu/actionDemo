package application;

import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	String labelRank[] = { "run ", "swim", "walk", "eat ", "play" }; // ProbabilityBarのランク5位まで
	String parcentRank[] = { "56", "32", "10", "8 ", "4 " }; // ProbabilityBarのランク5位まで


	@Override
	public void start(Stage primaryStage) throws Exception {

		try {

			BorderPane root = new BorderPane();
			root.setPadding(new Insets(30, 15, 80, 15));// (top,right,under,left)

			// 動画ファイルのパスを取得
			File f = new File("movie/oow2010-2.flv");
			VBox mediaPlayer = new VBox(10);
			mediaPlayer.setAlignment(Pos.CENTER);

			// 動画再生クラスをインスタンス化
			Label videoLabel = new Label("This is the Title of this Movie");
			videoLabel.setFont(new Font("Arial", 30));
			mediaPlayer.getChildren().addAll(videoLabel);

			VBox media = new VBox();
			// videoLabel.setStyle("-fx-background-color: red"); //check用
			Media Video = new Media(f.toURI().toString());
			MediaPlayer Play = new MediaPlayer(Video);
			MediaView mediaView = new MediaView(Play);
			mediaView.setFitWidth(1100);
			media.getChildren().addAll(mediaView);
			media.setPadding(new Insets(0, 20, 0, 20));
			mediaPlayer.getChildren().addAll(media);

			// movie下に表示する情報バーを作成
			HBox infoNode = new HBox(10.0);
			infoNode.setAlignment(Pos.CENTER);
			infoNode.getChildren().add(createButton(Play)); // 再生・停止・繰り返しボタン作成
			infoNode.getChildren().add(createTimeSlider(Play)); // 時間表示スライダ作成
			mediaPlayer.getChildren().addAll(infoNode);

			root.setCenter(mediaPlayer);

			// 画面下に表示するProbability labelとProbability Barを作成
			// 画面bottom部分を作成

			VBox bottomNode = new VBox(20);
			bottomNode.setAlignment(Pos.CENTER);
			HBox barNode0 = new HBox(20);
			HBox barNode1 = new HBox(20);
			HBox barNode2 = new HBox(20);
			HBox barNode3 = new HBox(20);
			HBox barNode4 = new HBox(20);

			ProgressBar probabilityBar0 = new ProgressBar();
			ProgressBar probabilityBar1 = new ProgressBar();
			ProgressBar probabilityBar2 = new ProgressBar();
			ProgressBar probabilityBar3 = new ProgressBar();
			ProgressBar probabilityBar4 = new ProgressBar();

			probabilityBar0.setPrefWidth(1000);
			probabilityBar0.setMaxWidth(1000);
			probabilityBar0.setPrefHeight(30);
			probabilityBar0.setProgress(0.0);

			probabilityBar1.setPrefWidth(1000);
			probabilityBar1.setMaxWidth(1000);
			probabilityBar1.setPrefHeight(30);
			probabilityBar1.setProgress(0.0);

			probabilityBar2.setPrefWidth(1000);
			probabilityBar2.setMaxWidth(1000);
			probabilityBar2.setPrefHeight(30);
			probabilityBar2.setProgress(0.0);

			probabilityBar3.setPrefWidth(1000);
			probabilityBar3.setMaxWidth(1000);
			probabilityBar3.setPrefHeight(30);
			probabilityBar3.setProgress(0.0);

			probabilityBar4.setPrefWidth(1000);
			probabilityBar4.setMaxWidth(1000);
			probabilityBar4.setPrefHeight(30);
			probabilityBar4.setProgress(0.0);
			
			Label lblMsg0 = new Label(labelRank[0]);
			Label parcent0 = new Label(parcentRank[0]);
			Label lblMsg1 = new Label(labelRank[1]);
			Label parcent1 = new Label(parcentRank[1]);
			Label lblMsg2 = new Label(labelRank[2]);
			Label parcent2 = new Label(parcentRank[2]);
			Label lblMsg3 = new Label(labelRank[3]);
			Label parcent3 = new Label(parcentRank[3]);
			Label lblMsg4 = new Label(labelRank[4]);
			Label parcent4 = new Label(parcentRank[4]);

			/*
			 * Label[] lblMsg = new Label[5]; for(int i=0 ; i<5;i++){
			 * lblMsg[i].setText(labelRank[i]); }
			 * 
			 * Label[] parcent = new Label[5]; for(int i=0 ; i<5;i++){
			 * parcent[i].setText(parcentRank[i]); }
			 */

			barNode0.setPadding(new Insets(40, 10, 5, 10));
			barNode0.setSpacing(20.0);
			barNode0.getChildren().add(lblMsg0);
			barNode0.getChildren().add(probabilityBar0);
			barNode0.getChildren().add(parcent0);
			barNode0.setAlignment(Pos.CENTER);

			barNode1.setPadding(new Insets(5, 10, 5, 10));
			barNode1.setSpacing(20.0);
			barNode1.getChildren().add(lblMsg1);
			barNode1.getChildren().add(probabilityBar1);
			barNode1.getChildren().add(parcent1);
			barNode1.setAlignment(Pos.CENTER);

			barNode2.setPadding(new Insets(5, 10, 5, 10));
			barNode2.setSpacing(20.0);
			barNode2.getChildren().add(lblMsg2);
			barNode2.getChildren().add(probabilityBar2);
			barNode2.getChildren().add(parcent2);
			barNode2.setAlignment(Pos.CENTER);

			barNode3.setPadding(new Insets(5, 10, 5, 10));
			barNode3.setSpacing(20.0);
			barNode3.getChildren().add(lblMsg3);
			barNode3.getChildren().add(probabilityBar3);
			barNode3.getChildren().add(parcent3);
			barNode3.setAlignment(Pos.CENTER);

			barNode4.setPadding(new Insets(5, 10, 5, 10));
			barNode4.setSpacing(20.0);
			barNode4.getChildren().add(lblMsg4);
			barNode4.getChildren().add(probabilityBar4);
			barNode4.getChildren().add(parcent4);
			barNode4.setAlignment(Pos.CENTER);

			bottomNode.setPadding(new Insets(5, 10, 5, 10));
			bottomNode.setSpacing(20.0);
			bottomNode.getChildren().add(barNode0);
			bottomNode.getChildren().add(barNode1);
			bottomNode.getChildren().add(barNode2);
			bottomNode.getChildren().add(barNode3);
			bottomNode.getChildren().add(barNode4);

			root.setBottom(bottomNode);

			// 画面右に表示するVideoListを作成
			VBox rightNode = new VBox(600);

			Label messageVL = new Label("VIDEO LIST");

			ObservableList<String> numbers = FXCollections.observableArrayList("one", "two", "three", "four", "five",
					"six", "seven", "eight", "nine", "ten", "one", "two", "three", "four", "five", "six", "seven",
					"eight", "nine", "ten");
			ListView<String> listView = new ListView<String>(numbers);
			listView.setPrefWidth(200);
			listView.setPrefHeight(500);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			listView.getSelectionModel().select(0);
			listView.setEditable(false);

			rightNode.setPadding(new Insets(10, 10, 10, 10));
			rightNode.setSpacing(20.0);
			rightNode.getChildren().addAll(messageVL);
			rightNode.setAlignment(Pos.CENTER);
			rightNode.getChildren().addAll(listView);
			root.setRight(rightNode);

			// シーンを追加
			Scene scene = new Scene(root, 700, 500);

			// ステージ設定
			primaryStage.setTitle("VideoPlay");
			primaryStage.setScene(scene);
			primaryStage.show();

			// 動画再生開始
			Play.play();

			// 動作確認用の出力を設定
			Play.currentTimeProperty().addListener((ov) -> System.out.println(Play.getCurrentTime()));
			Play.statusProperty().addListener((ov) -> System.out.println(Play.getStatus()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 再生、一時停止、停止、連続再生ボタンを作成
	 * 
	 * @param mp
	 * @return
	 */
	public Node createButton(MediaPlayer mp) {
		// 表示コンポーネントを作成
		HBox root = new HBox(1.0);
		Button playButton = new Button("Play");
		Button pauseButton = new Button("Pause");
		Button stopButton = new Button("Stop");
		ToggleButton repeatButton = new ToggleButton("Repeat");
		root.getChildren().add(playButton);
		root.getChildren().add(pauseButton);
		root.getChildren().add(stopButton);
		root.getChildren().add(repeatButton);

		// 再生ボタンにイベントを登録
		EventHandler<ActionEvent> playHandler = (e) -> {
			// 再生開始
			mp.play();
		};
		playButton.addEventHandler(ActionEvent.ACTION, playHandler);

		// 一時停止ボタンにイベントを登録
		EventHandler<ActionEvent> pauseHandler = (e) -> {
			// 一時停止
			mp.pause();
		};
		pauseButton.addEventHandler(ActionEvent.ACTION, pauseHandler);

		// 停止ボタンにイベントを登録
		EventHandler<ActionEvent> stopHandler = (e) -> {
			// 停止
			mp.stop();
		};
		stopButton.addEventHandler(ActionEvent.ACTION, stopHandler);

		// 連続再生設定
		Runnable repeatFunc = () -> {
			// 連続再生ボタンの状態を取得し
			if (repeatButton.isSelected()) {
				// 頭だしして再生
				mp.seek(mp.getStartTime());
				mp.play();
			} else {
				// 頭だしして停止
				mp.seek(mp.getStartTime());
				mp.stop();
			}
			;
		};
		mp.setOnEndOfMedia(repeatFunc);

		return root;
	}

	/**
	 * 再生時間を表示・操作するスライダを作成
	 * 
	 * @param mp
	 * @return
	 */
	public Node createTimeSlider(MediaPlayer mp) {
		// 表示コンポーネントを作成
		HBox root = new HBox(5.0);
		Slider slider = new Slider();
		Label info = new Label();
		slider.setPrefWidth(650);
		root.getChildren().add(slider);
		root.getChildren().add(info);

		// 再生準備完了時に各種情報を取得する関数を登録
		Runnable beforeFunc = mp.getOnReady(); // 現在のレディ関数
		Runnable readyFunc = () -> {
			// 先に登録された関数を実行
			if (beforeFunc != null) {
				beforeFunc.run();
			}

			// スライダの値を設定
			slider.setMin(mp.getStartTime().toSeconds());
			slider.setMax(mp.getStopTime().toSeconds());
			slider.setSnapToTicks(true);
		};
		mp.setOnReady(readyFunc);

		// 再生中にスライダを移動
		// プレイヤの現在時間が変更されるたびに呼び出されるリスナを登録
		ChangeListener<? super Duration> playListener = (ov, old, current) -> {
			System.out.println("here");
			// 動画の情報をラベル出力
			String infoStr = String.format("%4.2f", mp.getCurrentTime().toSeconds()) + "/"
					+ String.format("%4.2f", mp.getTotalDuration().toSeconds());
			info.setText(infoStr);

			// スライダを移動
			slider.setValue(mp.getCurrentTime().toSeconds());
		};
		mp.currentTimeProperty().addListener(playListener);

		// スライダを操作するとシークする
		EventHandler<MouseEvent> sliderHandler = (e) -> {
			// スライダを操作すると、シークする
			mp.seek(javafx.util.Duration.seconds(slider.getValue()));

		};
		slider.addEventFilter(MouseEvent.MOUSE_RELEASED, sliderHandler);

		return root;
	}

}