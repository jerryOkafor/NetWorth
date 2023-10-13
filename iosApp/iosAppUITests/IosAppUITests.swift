//
//  IosAppUITests.swift
//  iosAppUITests
//
//  Created by Jerry Okafor on 09/10/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

@testable import NetWorth
import XCTest

final class IosAppUITests: XCTestCase {

  override func setUpWithError() throws {
    // Put setup code here. This method is called before the invocation of each test method in the class.

    // In UI tests it is usually best to stop immediately when a failure occurs.
    continueAfterFailure = false

    // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests
    // before they run. The setUp method is a good place to do this.
  }

  override func tearDownWithError() throws {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
  }

  func testExample() throws {
    // UI tests must launch the application that they test.
    let app = XCUIApplication()
    app.launch()

    // Use XCTAssert and related functions to verify your tests produce the correct results.
  }

  func testLaunchPerformance() throws {
    if #available(iOS 14.0, *) {
      // This measures how long it takes to launch your application.
      measure(metrics: [XCTApplicationLaunchMetric()]) {
        XCUIApplication().launch()
      }
    }
  }
}
