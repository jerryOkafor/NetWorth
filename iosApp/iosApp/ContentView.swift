import SwiftUI
import UIKit
import shared

struct ComposeView: UIViewControllerRepresentable {
  func makeUIViewController(context _: Context) -> UIViewController {
    Main_iosKt.MainView()
  }

  func updateUIViewController(_: UIViewController, context _: Context) {}
}

struct ContentView: View {
  var body: some View {
    ComposeView().ignoresSafeArea()
  }
}
