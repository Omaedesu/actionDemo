package application;

import java.io.File;
import java.util.*;

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
	int verticalItemSize = 5;


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
			mediaPlayer.getChildren().addAll(videoLabel);//VBox型のmediaPlayerにvideoLabelを追加
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
			List<HBox> barNodes = new ArrayList<HBox>();
			List<ProgressBar> probabilityBars = new ArrayList<ProgressBar>();
			List<Label> lblMsgs = new ArrayList<Label>();
			List<Label> parcents = new ArrayList<Label>();
		
			// Initialize each items
			for (int i=0;i<verticalItemSize;++i){
				probabilityBars.add(new ProgressBar());
				probabilityBars.get(i).setPrefWidth(1000);
				probabilityBars.get(i).setMaxWidth(1000);
				probabilityBars.get(i).setPrefHeight(30);
				probabilityBars.get(i).setProgress(0.0);
				
				lblMsgs.add(new Label(labelRank[i]));
				parcents.add(new Label(parcentRank[i]));
				
				barNodes.add(new HBox());
				barNodes.get(i).setPadding(new Insets(5, 10, 5, 10));
				barNodes.get(i).setSpacing(20.0);
				barNodes.get(i).getChildren().add(lblMsgs.get(i));
				barNodes.get(i).getChildren().add(probabilityBars.get(i));
				barNodes.get(i).getChildren().add(parcents.get(i));
				barNodes.get(i).setAlignment(Pos.CENTER);
				
				bottomNode.getChildren().add(barNodes.get(i));
			}

			barNodes.get(0).setPadding(new Insets(40, 10, 5, 10));

			bottomNode.setPadding(new Insets(5, 10, 5, 10));
			bottomNode.setSpacing(20.0);

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